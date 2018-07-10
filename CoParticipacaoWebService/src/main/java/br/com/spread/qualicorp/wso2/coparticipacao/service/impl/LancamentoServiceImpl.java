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
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.LancamentoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.LancamentoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputLancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.BeneficiarioNotFoundException;
import br.com.spread.qualicorp.wso2.coparticipacao.io.impl.ProcessorListener;
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
public class LancamentoServiceImpl
		extends AbstractServiceImpl<LancamentoUi, LancamentoEntity, Lancamento>
		implements LancamentoService, ProcessorListener {

	private static final Logger LOGGER = LogManager
			.getLogger(LancamentoServiceImpl.class);

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

	public void processLine(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		Object value;
		List<InputLancamentoUi> inputLancamentoUis;
		LancamentoUi lancamentoUi;
		boolean foundColumnLancamento;

		try {
			LOGGER.info("BEGIN");

			lancamentoUi = new LancamentoUi();

			inputLancamentoUis = coParticipacaoContext.getInputLancamentoUis();

			if (!inputLancamentoUis.isEmpty()) {

				// Processando uma linha do arquivo:
				for (ArquivoInputColsDefUi arquivoInputColsDefUi : coParticipacaoContext
						.getArquivoInputColsDefUis()) {
					value = coParticipacaoContext.getMapLine()
							.get(arquivoInputColsDefUi.getNameColumn());

					LOGGER.debug(
							"Column [{}] with value [{}]:",
							arquivoInputColsDefUi.getNameColumn(),
							value);

					foundColumnLancamento = false;

					for (InputLancamentoUi inputLancamentoUi : inputLancamentoUis) {
						if (inputLancamentoUi.getArquivoInputColsDef().getId()
								.equals(arquivoInputColsDefUi.getId())) {
							storeInputValue(
									lancamentoUi,
									inputLancamentoUi,
									value,
									coParticipacaoContext);

							lancamentoDetailService.storeLancamentoDetail(
									lancamentoUi,
									arquivoInputColsDefUi,
									value,
									coParticipacaoContext.getUser());

							foundColumnLancamento = true;
						}
					}

					if (!foundColumnLancamento) {
						LOGGER.info(
								"Registro em InputLancamento informando a coluna de destino para [{}] não foi localizada.",
								arquivoInputColsDefUi.getNameColumn());

					}
				}
			}

			if (validateLancamento(coParticipacaoContext, lancamentoUi)) {

				// Aplicamdo regras do arquivo se existirem:
				regraService.applyRegras(lancamentoUi, coParticipacaoContext);

				lancamentoUi.setEmpresa(coParticipacaoContext.getEmpresaUi());
				lancamentoUi.setUserAltered(coParticipacaoContext.getUser());
				lancamentoUi.setUserCreated(coParticipacaoContext.getUser());
				lancamentoUi.setAltered(LocalDateTime.now());
				lancamentoUi.setCreated(LocalDateTime.now());

				coParticipacaoContext.addLancamento(lancamentoUi);
			} else {
				desconhecidoService.createDesconhecido(coParticipacaoContext);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private boolean validateLancamento(
			CoParticipacaoContext coParticipacaoContext,
			LancamentoUi lancamentoUi)
			throws ServiceException, BeneficiarioNotFoundException {
		boolean beneficiarioFound = false;

		if (lancamentoUi.getTitular() != null) {
			beneficiarioFound = true;
		} else {
			LOGGER.info(
					"Não foram encontrados beneficiários para a linha [%d]",
					coParticipacaoContext.getCurrentLine());
		}

		return beneficiarioFound;
	}

	private void storeInputValue(
			LancamentoUi lancamentoUi,
			InputLancamentoUi inputLancamentoUi,
			Object value,
			CoParticipacaoContext coParticipacaoContext)
			throws ServiceException, BeneficiarioNotFoundException {
		LancamentoColType lancamentoColType;

		try {
			LOGGER.info("BEGIN");
			lancamentoColType = LancamentoColType.parseByDescription(
					inputLancamentoUi.getLancamentoColsDef().getNameColumn());

			if (LancamentoColType.ID_BENEFICIARIO.equals(lancamentoColType)) {
				findBeneficiario(
						lancamentoUi,
						value.toString(),
						coParticipacaoContext);
			} else if (LancamentoColType.ID_CONTRATO
					.equals(lancamentoColType)) {
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

	private void findBeneficiario(
			LancamentoUi lancamentoUi,
			String cpf,
			CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		TitularUi titularUi;
		DependenteUi dependenteUi;

		try {
			LOGGER.info("BEGIN");

			titularUi = coParticipacaoContext.findTitularByCpf(cpf);

			if (titularUi == null) {
				LOGGER.info("O portador do CPF[%s] não é Titular.", cpf);

				dependenteUi = coParticipacaoContext.findDependenteByCpf(cpf);

				if (dependenteUi == null) {
					LOGGER.info(
							"O Dependente portador do CPF[%s] não foi encontrado.",
							cpf);
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

	public boolean validateLine(
			String line,
			CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		if (line.length() == coParticipacaoContext.getArquivoInputUi()
				.getDefaultLineLength()) {
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

	public void afterProcess(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Process ending and sending data to database:");

			if (coParticipacaoContext.getBunker().getLancamentoUis()
					.isEmpty()) {
				LOGGER.info("There is no valid lancamentos in this process:");
			} else {
				LOGGER.info("Storing lancamentos data:");
				save(coParticipacaoContext.getBunker().getLancamentoUis());
			}

			LOGGER.info("Storing desconhecidos data:");
			desconhecidoService.save(
					coParticipacaoContext.getBunker().getDesconhecidoUis());

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void beforeProcess(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		ArquivoInputUi arquivoInputUi;

		try {
			LOGGER.info("BEGIN");

			arquivoInputUi = coParticipacaoContext.getArquivoInputUi();

			LOGGER.info(
					"Starting process [{}] to load benefiets from assets file:",
					arquivoInputUi.getUseType().getDescription());

			LOGGER.info(
					"Cleaning all previous data from year[{}] and month[{}]:",
					coParticipacaoContext.getAno(),
					coParticipacaoContext.getMes());

			deleteByMesAndAno(
					coParticipacaoContext.getMes(),
					coParticipacaoContext.getAno());

			desconhecidoService.deleteByMesAndAno(
					coParticipacaoContext.getMes(),
					coParticipacaoContext.getAno());

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void deleteByMesAndAno(int mes, int ano) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			lancamentoDao.deleteByMesAndAno(mes, ano);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
