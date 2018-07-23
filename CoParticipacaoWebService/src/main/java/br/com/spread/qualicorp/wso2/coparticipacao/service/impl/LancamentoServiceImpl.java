package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.LancamentoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Lancamento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.LancamentoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.LancamentoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputLancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.BeneficiarioNotFoundException;
import br.com.spread.qualicorp.wso2.coparticipacao.io.impl.ProcessorListener;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.AbstractJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.LancamentoJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoOutputService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.BeneficiarioService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class LancamentoServiceImpl extends AbstractServiceImpl<LancamentoUi, LancamentoEntity, Lancamento> implements LancamentoService, ProcessorListener {

	private static final Logger LOGGER = LogManager.getLogger(LancamentoServiceImpl.class);

	@Autowired
	private LancamentoDao lancamentoDao;

	@Autowired
	private LancamentoDetailService lancamentoDetailService;

	@Autowired
	private LancamentoUiMapper uiMapper;

	@Autowired
	private LancamentoEntityMapper entityMapper;

	@Autowired
	private DesconhecidoService desconhecidoService;

	@Autowired
	private RegraService regraService;

	@Autowired
	private ArquivoOutputService arquivoOutputService;

	@Autowired
	private BeneficiarioService beneficiarioService;

	@Autowired
	private LancamentoJdbcDao lancamentoJdbcDao;

	public void processLine(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		Object value;
		List<InputLancamentoUi> inputLancamentoUis;
		LancamentoUi lancamentoUi;

		try {
			LOGGER.info("BEGIN");

			lancamentoUi = new LancamentoUi();

			inputLancamentoUis = coParticipacaoContext.getInputLancamentoUis();

			if (!inputLancamentoUis.isEmpty()) {

				// Processando uma linha do arquivo:
				for (ArquivoInputColsDefUi arquivoInputColsDefUi : coParticipacaoContext.getArquivoInputColsDefUis()) {
					value = coParticipacaoContext.getMapLine().get(arquivoInputColsDefUi.getNameColumn());

					LOGGER.debug("Column [{}] with value [{}]:", arquivoInputColsDefUi.getNameColumn(), value);

					for (InputLancamentoUi inputLancamentoUi : inputLancamentoUis) {
						if (inputLancamentoUi.getArquivoInputColsDef().getId().equals(arquivoInputColsDefUi.getId())) {
							storeInputValue(lancamentoUi, inputLancamentoUi, value, coParticipacaoContext);
						} else {
							LOGGER.info(
									"Registro em InputLancamento informando a coluna de destino para [{}] não foi localizada.",
									arquivoInputColsDefUi.getNameColumn());

						}
					}

					lancamentoDetailService.storeLancamentoDetail(lancamentoUi, arquivoInputColsDefUi, value, coParticipacaoContext.getUser());

				}
			}

			// Aplicamdo regras do arquivo se existirem:
			regraService.applyRegras(lancamentoUi, coParticipacaoContext);

			if (beneficiarioService.validateBeneficiario(coParticipacaoContext, lancamentoUi)) {
				if (lancamentoUi.getMes() == null) {
					lancamentoUi.setMes(coParticipacaoContext.getMes());
				}

				if (lancamentoUi.getAno() == null) {
					lancamentoUi.setAno(coParticipacaoContext.getAno());
				}

				lancamentoUi.setUserAltered(coParticipacaoContext.getUser());
				lancamentoUi.setUserCreated(coParticipacaoContext.getUser());
				lancamentoUi.setAltered(LocalDateTime.now());
				lancamentoUi.setCreated(LocalDateTime.now());

				lancamentoDetailService.showLancamentoDetailInfo(lancamentoUi);

				coParticipacaoContext.addLancamento(lancamentoUi);
			} else {
				desconhecidoService.createDesconhecido(coParticipacaoContext, lancamentoUi);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void storeInputValue(LancamentoUi lancamentoUi, InputLancamentoUi inputLancamentoUi, Object value, CoParticipacaoContext coParticipacaoContext)
			throws ServiceException, BeneficiarioNotFoundException {
		LancamentoColType lancamentoColType;

		try {
			LOGGER.info("BEGIN");
			lancamentoColType = LancamentoColType.parseByDescription(inputLancamentoUi.getLancamentoColsDef().getNameColumn());

			if (LancamentoColType.ID_BENEFICIARIO.equals(lancamentoColType)) {
				findBeneficiario(lancamentoUi, value.toString(), coParticipacaoContext);
			} else if (LancamentoColType.ID_CONTRATO.equals(lancamentoColType)) {
				lancamentoUi.setContrato(coParticipacaoContext.getContratoUi());
			} else if (LancamentoColType.CD_MES.equals(lancamentoColType)) {
				lancamentoUi.setMes((Integer) value);
			} else if (LancamentoColType.CD_ANO.equals(lancamentoColType)) {
				lancamentoUi.setAno((Integer) value);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void findBeneficiario(LancamentoUi lancamentoUi, String cpf, CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		TitularUi titularUi;
		DependenteUi dependenteUi;

		try {
			LOGGER.info("BEGIN");

			titularUi = coParticipacaoContext.findTitularByCpf(cpf);

			if (titularUi == null) {
				LOGGER.info("O portador do CPF[{}] não é Titular.", cpf);

				dependenteUi = coParticipacaoContext.findDependenteByCpf(cpf);

				if (dependenteUi == null) {
					LOGGER.info("O Dependente portador do CPF[{}] não foi encontrado.", cpf);
				} else {
					lancamentoUi.setTitular(dependenteUi.getTitular());
					lancamentoUi.setDependente(dependenteUi);
				}
			} else {
				lancamentoUi.setTitular(titularUi);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public boolean validateLine(String line, CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		if (coParticipacaoContext.getArquivoInputUi().getDefaultLineLength() != null) {
			if (line.length() == coParticipacaoContext.getArquivoInputUi().getDefaultLineLength()) {
				return true;
			}
		} else {
			return true;
		}

		return false;
	}

	@Override
	protected LancamentoUi createUi() {
		return new LancamentoUi();
	}

	@Override
	protected LancamentoEntity createEntity() {
		return new LancamentoEntity();
	}

	@Override
	protected AbstractDao<LancamentoEntity> getDao() {
		return lancamentoDao;
	}

	@Override
	protected AbstractMapper<Lancamento, LancamentoUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<Lancamento, LancamentoEntity> getEntityMapper() {
		return entityMapper;
	}

	public void afterProcess(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Process ending and sending data to database:");

			if (coParticipacaoContext.getBunker().getLancamentoUis().isEmpty()) {
				LOGGER.info("There is no valid lancamentos in this process:");
			} else {
				LOGGER.info("Storing lancamentos data:");
				saveBatch(coParticipacaoContext.getBunker().getLancamentoUis());
			}

			LOGGER.info("Storing desconhecidos data:");
			desconhecidoService.saveBatch(coParticipacaoContext.getBunker().getDesconhecidoUis());

			writeOutputFiles(coParticipacaoContext);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void beforeProcess(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		ArquivoInputUi arquivoInputUi;

		try {
			LOGGER.info("BEGIN");

			arquivoInputUi = coParticipacaoContext.getArquivoInputUi();

			LOGGER.info("Starting process [{}] to load benefiets from assets file:", arquivoInputUi.getUseType().getDescription());

			LOGGER.info("Cleaning all previous data from year[{}] and month[{}]:", coParticipacaoContext.getAno(), coParticipacaoContext.getMes());

			deleteByMesAndAno(coParticipacaoContext.getContratoUi(), coParticipacaoContext.getMes(), coParticipacaoContext.getAno());

			desconhecidoService.deleteByMesAndAno(coParticipacaoContext.getContratoUi(), coParticipacaoContext.getMes(), coParticipacaoContext.getAno());

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void deleteByMesAndAno(ContratoUi contratoUi, int mes, int ano) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			lancamentoDao.deleteByMesAndAno(contratoUi.getId(), mes, ano);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void writeOutputFiles(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			desconhecidoService.writeDesconhecidosFile(coParticipacaoContext);

			arquivoOutputService.writeOutputFile(coParticipacaoContext);

			LOGGER.info("Process finished with success, all output files created:");
			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public void saveBatch(List<LancamentoUi> lancamentoUis) throws ServiceException {
		super.saveBatch(lancamentoUis);

		for (LancamentoUi lancamentoUi : lancamentoUis) {
			for (LancamentoDetail lancamentoDetail : lancamentoUi.getLancamentoDetails()) {
				lancamentoDetailService.saveBatch((LancamentoDetailUi) lancamentoDetail);
			}
		}
	}

	@Override
	protected AbstractJdbcDao<LancamentoEntity> getJdbcDao() {
		return lancamentoJdbcDao;
	}

	@Override
	protected void logBatchInfo(LancamentoUi lancamentoUi) throws ServiceException {
		TitularUi titularUi = (TitularUi) lancamentoUi.getTitular();
		DependenteUi dependenteUi = (DependenteUi) lancamentoUi.getDependente();

		LOGGER.debug("ID ....................... [{}]:", lancamentoUi.getId());
		LOGGER.debug("CD-CONTRATO .............. [{}]:", lancamentoUi.getContrato().getCdContrato());
		LOGGER.debug("CD_MES ................... [{}]:", lancamentoUi.getMes());
		LOGGER.debug("CD_ANO ................... [{}]:", lancamentoUi.getAno());
		LOGGER.debug("ID_TITULAR ............... [{}]:", titularUi.getId());
		LOGGER.debug("NM_TITULAR ............... [{}]:", titularUi.getNameTitular());
		LOGGER.debug("CPF_TITULAR .............. [{}]:", titularUi.getCpf());
		LOGGER.debug("MATRICULA_TITULAR ........ [{}]:", titularUi.getMatricula());
		LOGGER.debug("DT_NASC_TITULAR .......... [{}]:", titularUi.getDtNascimento());
		LOGGER.debug("DT_NASC_ADMISSAO ......... [{}]:", titularUi.getDtAdmissao());

		if (dependenteUi != null) {
			LOGGER.debug("ID_DEPENDENTE ............ [{}]:", dependenteUi.getId());
			LOGGER.debug("NM_DEPENDENTE ............ [{}]:", dependenteUi.getNameDependente());
			LOGGER.debug("CPF_DEPENDENTE ........... [{}]:", dependenteUi.getCpf());
			LOGGER.debug("MATRICULA_DEPENDENTE ..... [{}]:", dependenteUi.getMatricula());
			LOGGER.debug("DT_NASC_DEPENDENTE ....... [{}]:", dependenteUi.getDtNascimento());
		}

		LOGGER.debug("VL_PRINCIPAL ............. [{}]:", "");

	}

}
