package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.DesconhecidoJdbcDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DesconhecidoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Desconhecido;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DesconhecidoColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DynamicEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.StatusExecucaoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DesconhecidoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.DesconhecidoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.DesconhecidoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputDesconhecidoSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputDesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ViewDestinationColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ViewDestinationUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoExecucaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoOutputDesconhecidoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoOutputDesconhecidoSheetService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoOutputService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ViewDestinationColsDefService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ViewDestinationService;
import br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet.DesconhecidoSpreadsheetListener;
import br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet.SpreadsheetBuilder;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class DesconhecidoServiceImpl extends AbstractServiceImpl<DesconhecidoUi, DesconhecidoEntity, Desconhecido>
		implements DesconhecidoService {

	private static final Logger LOGGER = LogManager.getLogger(DesconhecidoServiceImpl.class);

	@Autowired
	private DesconhecidoUiMapper desconhecidoUiMapper;

	@Autowired
	private DesconhecidoEntityMapper desconhecidoEntityMapper;

	@Autowired
	private DesconhecidoDao desconhecidoDao;

	@Autowired
	private ArquivoOutputDesconhecidoSheetService arquivoOutputDesconhecidoSheetService;

	@Autowired
	private ArquivoOutputDesconhecidoService arquivoOutputDesconhecidoService;

	@Autowired
	private LancamentoDetailService lancamentoDetailService;

	@Autowired
	private DesconhecidoJdbcDao desconhecidoJdbcDao;

	@Autowired
	private ViewDestinationService viewDestinationService;

	@Autowired
	private ViewDestinationColsDefService viewDestinationColsDefService;

	@Autowired
	private ArquivoExecucaoService arquivoExecucaoService;

	@Autowired
	private ArquivoOutputService arquivoOutputService;

	public DesconhecidoServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected DesconhecidoUi createUi() {
		return new DesconhecidoUi();
	}

	@Override
	protected DesconhecidoEntity createEntity() {
		return new DesconhecidoEntity();
	}

	@Override
	protected AbstractDao<DesconhecidoEntity> getDao() {
		return desconhecidoDao;
	}

	@Override
	protected AbstractMapper<Desconhecido, DesconhecidoUi> getUiMapper() {
		return desconhecidoUiMapper;
	}

	@Override
	protected AbstractMapper<Desconhecido, DesconhecidoEntity> getEntityMapper() {
		return desconhecidoEntityMapper;
	}

	public void createDesconhecido(CoParticipacaoContext coParticipacaoContext, LancamentoUi lancamentoUi)
			throws ServiceException {
		DesconhecidoUi desconhecidoUi;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info(
					"Creating Desconhecido register for beneficiario at line [{}]:",
					coParticipacaoContext.getCurrentLine());

			lancamentoDetailService.showLancamentoDetailInfo(lancamentoUi);

			desconhecidoUi = new DesconhecidoUi();
			desconhecidoUi.setMes(coParticipacaoContext.getMes());
			desconhecidoUi.setAno(coParticipacaoContext.getAno());
			desconhecidoUi.setContrato(coParticipacaoContext.getContratoUi());
			desconhecidoUi.setUserCreated(coParticipacaoContext.getUser());
			desconhecidoUi.setUserAltered(coParticipacaoContext.getUser());
			desconhecidoUi.setValorPrincipal(lancamentoUi.getValorPrincipal());

			if (coParticipacaoContext.getBeneficiarioUi() != null) {
				createDesconhecido(desconhecidoUi, coParticipacaoContext.getBeneficiarioUi());
			}

			coParticipacaoContext.addDesconhecido(desconhecidoUi);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void createDesconhecido(CoParticipacaoContext coParticipacaoContext, TitularUi titularUi)
			throws ServiceException {
		DesconhecidoUi desconhecidoUi;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info(
					"Creating Desconhecido register for beneficiario at line [{}]:",
					coParticipacaoContext.getCurrentLine());

			desconhecidoUi = new DesconhecidoUi();
			desconhecidoUi.setMes(coParticipacaoContext.getMes());
			desconhecidoUi.setAno(coParticipacaoContext.getAno());
			desconhecidoUi.setContrato(coParticipacaoContext.getContratoUi());
			desconhecidoUi.setUserCreated(coParticipacaoContext.getUser());
			desconhecidoUi.setUserAltered(coParticipacaoContext.getUser());

			createDesconhecido(desconhecidoUi, titularUi);

			coParticipacaoContext.addDesconhecido(desconhecidoUi);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void createDesconhecido(CoParticipacaoContext coParticipacaoContext, DependenteUi dependenteUi)
			throws ServiceException {
		DesconhecidoUi desconhecidoUi;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info(
					"Creating Desconhecido register for beneficiario at line [{}]:",
					coParticipacaoContext.getCurrentLine());

			desconhecidoUi = new DesconhecidoUi();
			desconhecidoUi.setMes(coParticipacaoContext.getMes());
			desconhecidoUi.setAno(coParticipacaoContext.getAno());
			desconhecidoUi.setContrato(coParticipacaoContext.getContratoUi());
			desconhecidoUi.setUserCreated(coParticipacaoContext.getUser());
			desconhecidoUi.setUserAltered(coParticipacaoContext.getUser());

			createDesconhecido(desconhecidoUi, dependenteUi);

			coParticipacaoContext.addDesconhecido(desconhecidoUi);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void createDesconhecido(DesconhecidoUi desconhecidoUi, TitularUi titularUi) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			desconhecidoUi.setNameBeneficiario(titularUi.getNameTitular());
			desconhecidoUi.setCpf(titularUi.getCpf());
			desconhecidoUi.setMatricula(titularUi.getMatricula());
			desconhecidoUi.setMatriculaEmpresa(titularUi.getMatriculaEmpresa());
			desconhecidoUi.setDtNascimento(titularUi.getDtNascimento());

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void createDesconhecido(DesconhecidoUi desconhecidoUi, DependenteUi dependenteUi) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			desconhecidoUi.setNameBeneficiario(dependenteUi.getNameDependente());
			desconhecidoUi.setCpf(dependenteUi.getCpf());
			desconhecidoUi.setMatricula(dependenteUi.getMatricula());
			desconhecidoUi.setMatriculaEmpresa(dependenteUi.getMatriculaEmpresa());
			desconhecidoUi.setDtNascimento(dependenteUi.getDtNascimento());

			if (dependenteUi.getTitular() != null) {
				createDesconhecido(desconhecidoUi, (TitularUi) dependenteUi.getTitular());
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void createDesconhecido(DesconhecidoUi desconhecidoUi, BeneficiarioUi beneficiarioUi)
			throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			desconhecidoUi.setNameBeneficiario(beneficiarioUi.getNameBeneficiario());
			desconhecidoUi.setCpf(beneficiarioUi.getCpf());
			desconhecidoUi.setMatricula(beneficiarioUi.getMatricula());
			desconhecidoUi.setMatriculaEmpresa(beneficiarioUi.getMatriculaEmpresa());
			desconhecidoUi.setDtNascimento(beneficiarioUi.getDtNascimento());
			desconhecidoUi.setNameTitular(beneficiarioUi.getNameTitular());

			desconhecidoUi.setDtAdmissao(beneficiarioUi.getDtAdmissao());
			desconhecidoUi.setReferenceCode(beneficiarioUi.getReferenceCode());

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void deleteByMesAndAno(ContratoUi contratoUi, int mes, int ano) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			desconhecidoJdbcDao.deleteByMesAndAno(contratoUi.getId(), mes, ano);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<DesconhecidoUi> listByMesAndAno(ContratoUi contratoUi, int mes, int ano) throws ServiceException {
		List<DesconhecidoUi> desconhecidoUis;

		try {
			LOGGER.info("BEGIN");

			desconhecidoUis = entityToUi(desconhecidoDao.listByContratoId(contratoUi.getId()));

			LOGGER.info("END");
			return desconhecidoUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void writeDesconhecidosFile(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		SpreadsheetBuilder<DynamicEntity> spreadsheetBuilder;
		List<ArquivoOutputDesconhecidoSheetUi> arquivoOutputDesconhecidoSheetUis;
		ArquivoOutputDesconhecidoUi arquivoOutputDesconhecidoUi;
		String nameOutputFile = null;
		ViewDestinationUi viewDestinationUi;
		List<ViewDestinationColsDefUi> viewDestinationColsDefUis;
		List<DynamicEntity> dynamicEntities;
		ArquivoExecucaoUi arquivoExecucaoUi;
		ArquivoExecucaoUi arquivoExecucaoUiTmp;

		try {
			LOGGER.info("BEGIN");

			LOGGER.info(
					"Using ArquivoInput [{}] to find ArquivoOutputDesconhecido need data:",
					coParticipacaoContext.getArquivoInputUi().getDescrArquivo());

			spreadsheetBuilder = new SpreadsheetBuilder<DynamicEntity>();

			arquivoOutputDesconhecidoUi = arquivoOutputDesconhecidoService
					.findByContratoId(coParticipacaoContext.getContratoUi());
			nameOutputFile = arquivoOutputDesconhecidoUi.getNameArquivoFormat();

			LOGGER.info("Creating ArquivoExecucaoUi for Desconhecidos data:");
			arquivoExecucaoUiTmp = coParticipacaoContext.getArquivoExecucaoUi();
			arquivoExecucaoUi = arquivoExecucaoService
					.createArquivoExecucao(coParticipacaoContext, UseType.NAO_LOCALIZADO);

			coParticipacaoContext.setArquivoExecucaoUi(arquivoExecucaoUi);
			arquivoExecucaoService.updateStatus(coParticipacaoContext, StatusExecucaoType.STARTED);
			arquivoExecucaoService.updateStatus(coParticipacaoContext, StatusExecucaoType.RUNNING);

			if (arquivoOutputDesconhecidoUi != null) {
				for (Contrato contrato : coParticipacaoContext.getEmpresaUi().getContratos()) {
					if (UseType.FATUCOPA.equals(contrato.getUseType())) {
						LOGGER.info("Configuring sheet [{}] for Desconhecidos:", contrato.getCdContrato());

						if (contrato.getArquivoInput() != null) {
							arquivoOutputDesconhecidoSheetUis = arquivoOutputDesconhecidoSheetService
									.listByArquivoInputId((ArquivoInputUi) contrato.getArquivoInput());

							if (!arquivoOutputDesconhecidoSheetUis.isEmpty()) {
								for (ArquivoOutputDesconhecidoSheetUi arquivoOutputDesconhecidoSheetUi : arquivoOutputDesconhecidoSheetUis) {

									viewDestinationUi = (ViewDestinationUi) arquivoOutputDesconhecidoSheetUi
											.getViewDestination();
									viewDestinationColsDefUis = viewDestinationColsDefService
											.listByViewDestinationId(viewDestinationUi);

									LOGGER.info(
											"Creating the report for the ViewDestination [{}]:",
											viewDestinationUi.getNameView());
									dynamicEntities = viewDestinationService.listByContratoAndMesAndAno(
											viewDestinationUi,
											(ContratoUi) contrato,
											coParticipacaoContext.getMes(),
											coParticipacaoContext.getAno());

									spreadsheetBuilder.addSpreadsheetListener(
											new DesconhecidoSpreadsheetListener(
													viewDestinationColsDefUis,
													dynamicEntities,
													(ContratoUi) contrato,
													coParticipacaoContext));
								}
							} else {
								LOGGER.info(
										"Contrato [{}] doesn't have an OutputFile mapped:",
										contrato.getCdContrato());
							}
						} else {
							LOGGER.info("Contrato [{}] doesn't have an ArquivoInput mapped:", contrato.getCdContrato());
						}
					}
				}
			} else {
				LOGGER.info(
						"The ArquivoInput[{}] and Contrato [{}] does not have a ArquivoOutput defined to it:",
						coParticipacaoContext.getArquivoInputUi().getDescrArquivo(),
						coParticipacaoContext.getContratoUi().getCdContrato());
			}

			LOGGER.info("Writing spreadsheet to filesystem:");
			spreadsheetBuilder.setSpreadsheetFileName(nameOutputFile);
			spreadsheetBuilder.writeSpreadsheet(coParticipacaoContext, arquivoOutputService);

			LOGGER.info("Updating ArquivoExecucaoUi with SUCCESS information:");
			arquivoExecucaoService.updateStatus(coParticipacaoContext, StatusExecucaoType.SUCCESS);
			coParticipacaoContext.setArquivoExecucaoUi(arquivoExecucaoUiTmp);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);

			coParticipacaoContext.getArquivoExecucaoUi().setErrorMessage(e.getMessage());
			arquivoExecucaoService.updateStatus(coParticipacaoContext, StatusExecucaoType.ERROR);

			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	protected void logBatchInfo(DesconhecidoUi desconhecidoUi) throws ServiceException {
		LOGGER.debug("ID:................................... [{}]", desconhecidoUi.getId());
		LOGGER.debug("CD_CONTRATO:.......................... [{}]", desconhecidoUi.getContrato().getCdContrato());
		LOGGER.debug("CD_MES:............................... [{}]", desconhecidoUi.getMes());
		LOGGER.debug("CD_ANO:............................... [{}]", desconhecidoUi.getAno());

		LOGGER.debug("DESCONHECIDO.NR_CPF:.................. [{}]", desconhecidoUi.getCpf());
		LOGGER.debug("DESCONHECIDO.NAME:.................... [{}]", desconhecidoUi.getNameBeneficiario());
		LOGGER.debug("DESCONHECIDO.NAME_TITULAR:............ [{}]", desconhecidoUi.getNameTitular());
		LOGGER.debug("DESCONHECIDO.DT_NASCIMENTO:........... [{}]", desconhecidoUi.getDtNascimento());
		LOGGER.debug("DESCONHECIDO.NR_MATRICULA:............ [{}]", desconhecidoUi.getMatricula());
		LOGGER.debug("DESCONHECIDO.NR_MATRICULA_EMPRESA:.... [{}]", desconhecidoUi.getMatriculaEmpresa());

		LOGGER.debug("DESCONHECIDO.DT_ADMISSAO:............. [{}]", desconhecidoUi.getDtAdmissao());
		LOGGER.debug("DESCONHECIDO.NR_CODE_REF:............. [{}]", desconhecidoUi.getReferenceCode());
	}

	public void createDesconhecido(CoParticipacaoContext coParticipacaoContext, BeneficiarioUi beneficiarioUi)
			throws ServiceException {
		DesconhecidoUi desconhecidoUi;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info(
					"Creating Desconhecido register for beneficiario at line [{}]:",
					coParticipacaoContext.getCurrentLine());

			desconhecidoUi = new DesconhecidoUi();
			desconhecidoUi.setMes(coParticipacaoContext.getMes());
			desconhecidoUi.setAno(coParticipacaoContext.getAno());
			desconhecidoUi.setContrato(coParticipacaoContext.getContratoUi());
			desconhecidoUi.setUserCreated(coParticipacaoContext.getUser());
			desconhecidoUi.setUserAltered(coParticipacaoContext.getUser());

			createDesconhecido(desconhecidoUi, beneficiarioUi);

			coParticipacaoContext.addDesconhecido(desconhecidoUi);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public Object getValueFromField(DesconhecidoUi desconhecidoUi, DesconhecidoColType desconhecidoColType)
			throws ServiceException {
		Object value = null;

		try {
			LOGGER.info("BEGIN");

			if (DesconhecidoColType.NM_BENEFICIARIO.equals(desconhecidoColType)) {
				value = desconhecidoUi.getNameBeneficiario();
			} else if (DesconhecidoColType.NR_CPF.equals(desconhecidoColType)) {
				value = desconhecidoUi.getCpf();
			} else if (DesconhecidoColType.NR_MATRICULA.equals(desconhecidoColType)) {
				value = desconhecidoUi.getMatricula();
			} else if (DesconhecidoColType.DT_NASCIMENTO.equals(desconhecidoColType)) {
				value = desconhecidoUi.getDtNascimento();
			} else if (DesconhecidoColType.VL_PRINCIPAL.equals(desconhecidoColType)) {
				value = desconhecidoUi.getValorPrincipal();
			} else if (DesconhecidoColType.DT_ADMISSAO.equals(desconhecidoColType)) {
				value = desconhecidoUi.getDtAdmissao();
			} else if (DesconhecidoColType.NR_REF_CODE.equals(desconhecidoColType)) {
				value = desconhecidoUi.getReferenceCode();
			}

			LOGGER.info("END");
			return value;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<DesconhecidoUi> listByEmpresaId(EmpresaUi empresaUi) throws ServiceException {
		List<DesconhecidoUi> desconhecidoUis;

		try {
			LOGGER.info("BEGIN");

			desconhecidoUis = entityToUi(desconhecidoDao.listByEmpresaId(empresaUi.getId()));

			LOGGER.info("END");
			return desconhecidoUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<DesconhecidoUi> listByEmpresaIdAndUseType(EmpresaUi empresaUi, UseType useType)
			throws ServiceException {
		List<DesconhecidoUi> desconhecidoUis;

		try {
			LOGGER.info("BEGIN");

			desconhecidoUis = entityToUi(desconhecidoDao.listByEmpresaIdAndUseType(empresaUi.getId(), useType));

			LOGGER.info("END");
			return desconhecidoUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
