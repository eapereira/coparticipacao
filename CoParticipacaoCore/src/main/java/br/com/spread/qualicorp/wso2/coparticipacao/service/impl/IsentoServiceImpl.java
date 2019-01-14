package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.DependenteBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.DependenteIsentoBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.TitularBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.TitularIsentoBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioIsentoColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.IsentoInputSheetCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.IsentoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.IsentoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegisterColumnUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.EndProcessException;
import br.com.spread.qualicorp.wso2.coparticipacao.io.ProcessLineResult;
import br.com.spread.qualicorp.wso2.coparticipacao.service.AbstractService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.BeneficiarioService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteIsentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.IsentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularIsentoService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class IsentoServiceImpl implements IsentoService {

	private static final Logger LOGGER = LogManager.getLogger(IsentoServiceImpl.class);

	@Autowired
	private DependenteIsentoService dependenteIsentoService;

	@Autowired
	private TitularIsentoService titularIsentoService;

	@Autowired
	private DependenteIsentoBatchService dependenteIsentoBatchService;

	@Autowired
	private TitularIsentoBatchService titularIsentoBatchService;

	@Autowired
	private RegraService regraService;

	@Autowired
	private BeneficiarioService beneficiarioService;

	@Autowired
	private TitularBatchService titularBatchService;

	@Autowired
	private DependenteBatchService dependenteBatchService;

	public ProcessLineResult processLine(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		IsentoInputSheetUi isentoInputSheetUi;
		List<IsentoInputSheetCols> isentoInputSheetCols;
		BeneficiarioIsentoUi beneficiarioIsentoUi = null;
		ArquivoInputSheetUi arquivoInputSheetUi;
		ProcessLineResult processLineResult = ProcessLineResult.READ_NEXT;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Stating Isento processing:");

			isentoInputSheetCols = coParticipacaoContext
					.listIsentoInputSheetColsBySheetId(coParticipacaoContext.getCurrentSheet());

			arquivoInputSheetUi = coParticipacaoContext.getArquivoInputSheet();
			isentoInputSheetUi = (IsentoInputSheetUi) arquivoInputSheetUi.getIsentoInputSheet();

			regraService.applyRegras(coParticipacaoContext);

			beneficiarioIsentoUi = createBeneficiarioIsento(coParticipacaoContext, isentoInputSheetCols);

			if (beneficiarioIsentoUi != null) {
				LOGGER.info(
						"Validating BeneficiarioIsentoUi NM_TITULAR[{}] and NM_BENEFICIARIO[{}], NR_MATRICULA[{}] and NR_CPF[{}]:",
						beneficiarioIsentoUi.getNameTitular(),
						beneficiarioIsentoUi.getName(),
						beneficiarioIsentoUi.getMatricula(),
						beneficiarioIsentoUi.getCpf());

				if (StringUtils.isBlank(beneficiarioIsentoUi.getName())
						&& StringUtils.isBlank(beneficiarioIsentoUi.getNameTitular())) {
					throw new EndProcessException(
							"Found a BeneficiarioIsentoUi without DependenteUi.NAME nad TitularUi.NAME, closing task.");
				}

				if (beneficiarioIsentoUi.getIsentoType() == null) {
					beneficiarioIsentoUi.setIsentoType(isentoInputSheetUi.getIsentoType());
				}

				if (beneficiarioIsentoUi.getIsentoType() == null) {
					throw new ServiceException(
							"There is no ISENTO_TYPE defined for BeneficiarioIsentoUi[{%s]:",
							beneficiarioIsentoUi.getName());
				}

				if (beneficiarioService.isTitular(coParticipacaoContext, beneficiarioIsentoUi)) {
					createTitularIsento(coParticipacaoContext, beneficiarioIsentoUi);
				} else {
					LOGGER.info(
							"BeneficiárioIsento [{}] user of CPF[{}] is not a titular:",
							beneficiarioIsentoUi.getName(),
							beneficiarioIsentoUi.getCpf());

					createDependenteIsento(coParticipacaoContext, beneficiarioIsentoUi);
				}
			} else {
				LOGGER.info(
						"Couldn't find a suitable BeneficiarioIsentoInputCols to convert row data into BeneficiarioIsento.");
			}

			LOGGER.info("END");
			return processLineResult;
		} catch (EndProcessException e) {
			LOGGER.info(e.getMessage());
			return ProcessLineResult.END_OF_SHEET;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void createDependenteIsento(
			CoParticipacaoContext coParticipacaoContext,
			BeneficiarioIsentoUi beneficiarioIsentoUi) throws ServiceException {
		DependenteIsentoUi dependenteIsentoUi;
		DependenteUi dependenteUi;

		try {
			LOGGER.info("BEGIN");

			dependenteUi = beneficiarioService.findDependente(coParticipacaoContext, beneficiarioIsentoUi);

			if (dependenteUi == null) {
				if (coParticipacaoContext.getEmpresaUi().isCreateBeneficiarioFromIsento()) {
					dependenteUi = beneficiarioService.createDependente(coParticipacaoContext, beneficiarioIsentoUi);
				}
			}

			if (dependenteUi != null) {
				dependenteIsentoUi = coParticipacaoContext.findDependenteIsento(dependenteUi);

				if (dependenteIsentoUi == null) {
					dependenteIsentoUi = new DependenteIsentoUi();
					dependenteIsentoUi.setDependente(dependenteUi);
					dependenteIsentoUi.setIsentoType(beneficiarioIsentoUi.getIsentoType());

					dependenteIsentoUi.setContrato(coParticipacaoContext.getContratoUi());
					dependenteIsentoUi.setMes(coParticipacaoContext.getMes());
					dependenteIsentoUi.setAno(coParticipacaoContext.getAno());

					dependenteIsentoUi.setDtInicio(beneficiarioIsentoUi.getDtInicio());
					dependenteIsentoUi.setDtFim(beneficiarioIsentoUi.getDtFim());

					dependenteIsentoUi.setUserCreated(coParticipacaoContext.getUser());
					dependenteIsentoUi.setUserAltered(coParticipacaoContext.getUser());

					coParticipacaoContext.addDependenteIsento(dependenteIsentoUi);
				}
			} else {
				LOGGER.info(
						"BeneficiárioIsento [{}] user of CPF[{}] is not a Dependente:",
						beneficiarioIsentoUi.getName(),
						beneficiarioIsentoUi.getCpf());
				LOGGER.info("Beneficiario not exists in database:");
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void createTitularIsento(
			CoParticipacaoContext coParticipacaoContext,
			BeneficiarioIsentoUi beneficiarioIsentoUi) throws ServiceException {
		TitularIsentoUi titularIsentoUi;
		TitularUi titularUi;

		try {
			LOGGER.info("BEGIN");

			titularUi = beneficiarioService.findTitular(coParticipacaoContext, beneficiarioIsentoUi);

			if (titularUi == null) {
				if (coParticipacaoContext.getEmpresaUi().isCreateBeneficiarioFromIsento()) {
					titularUi = beneficiarioService.createTitular(coParticipacaoContext, beneficiarioIsentoUi);
				}
			}

			if (titularUi != null) {
				titularIsentoUi = coParticipacaoContext.findTitularIsento(titularUi);

				if (titularIsentoUi == null) {
					titularIsentoUi = new TitularIsentoUi();

					titularIsentoUi.setTitular(titularUi);
					titularIsentoUi.setIsentoType(beneficiarioIsentoUi.getIsentoType());

					titularIsentoUi.setContrato(coParticipacaoContext.getContratoUi());
					titularIsentoUi.setMes(coParticipacaoContext.getMes());
					titularIsentoUi.setAno(coParticipacaoContext.getAno());
					titularIsentoUi.setValorIsencao(beneficiarioIsentoUi.getValorIsencao());

					titularIsentoUi.setDtInicio(beneficiarioIsentoUi.getDtInicio());
					titularIsentoUi.setDtFim(beneficiarioIsentoUi.getDtFim());

					titularIsentoUi.setUserCreated(coParticipacaoContext.getUser());
					titularIsentoUi.setUserAltered(coParticipacaoContext.getUser());

					coParticipacaoContext.addTitularIsento(titularIsentoUi);
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private BeneficiarioIsentoUi createBeneficiarioIsento(
			CoParticipacaoContext coParticipacaoContext,
			List<IsentoInputSheetCols> isentoInputSheetCols) throws ServiceException {
		BeneficiarioIsentoUi beneficiarioIsentoUi;
		BeneficiarioIsentoColType beneficiarioIsentoColType;
		Object value;

		try {
			LOGGER.info("BEGIN");

			beneficiarioIsentoUi = new BeneficiarioIsentoUi();

			for (IsentoInputSheetCols beneficiarioIsentoInputCol : isentoInputSheetCols) {
				beneficiarioIsentoColType = beneficiarioIsentoInputCol.getBeneficiarioIsentoColType();

				if (beneficiarioIsentoColType != null) {
					value = coParticipacaoContext
							.getColumnValue((RegisterColumnUi) beneficiarioIsentoInputCol.getRegisterColumn());

					LOGGER.info(
							"Transfering value [{}] to BeneficiarioIsento [{}]:",
							value,
							beneficiarioIsentoColType.getDescription());

					setBeneficiarioIsentoValue(beneficiarioIsentoColType, beneficiarioIsentoUi, value);
				}
			}

			LOGGER.info("END");
			return beneficiarioIsentoUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void setBeneficiarioIsentoValue(
			BeneficiarioIsentoColType beneficiarioIsentoColType,
			BeneficiarioIsentoUi beneficiarioIsentoUi,
			Object value) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (BeneficiarioIsentoColType.NR_MATRICULA.equals(beneficiarioIsentoColType)) {
				beneficiarioIsentoUi.setMatricula((Long) value);
			} else if (BeneficiarioIsentoColType.NR_CPF.equals(beneficiarioIsentoColType)) {
				if (value instanceof String) {
					if (StringUtils.isNotBlank((String) value)) {
						value = StringUtils.replaceAll((String) value, "(\\.|\\-)", "");
						beneficiarioIsentoUi.setCpf(Long.valueOf((String) value));
					}
				} else {
					beneficiarioIsentoUi.setCpf((Long) value);
				}
			} else if (BeneficiarioIsentoColType.NM_BENEFICIARIO.equals(beneficiarioIsentoColType)) {
				beneficiarioIsentoUi.setName((String) value);
			} else if (BeneficiarioIsentoColType.DT_NASCIMENTO.equals(beneficiarioIsentoColType)) {
				beneficiarioIsentoUi.setDtNascimento((LocalDate) value);
			} else if (BeneficiarioIsentoColType.TP_ISENTO.equals(beneficiarioIsentoColType)) {
				if (value instanceof String) {
					beneficiarioIsentoUi.setIsentoType(IsentoType.parse(((String) value).toUpperCase()));
				} else {
					beneficiarioIsentoUi.setIsentoType(IsentoType.parse((Integer) value));
				}
			} else if (BeneficiarioIsentoColType.NR_MATRICULA_TITULAR.equals(beneficiarioIsentoColType)) {
				beneficiarioIsentoUi.setMatriculaTitular((Long) value);
			} else if (BeneficiarioIsentoColType.NM_TITULAR.equals(beneficiarioIsentoColType)) {
				beneficiarioIsentoUi.setNameTitular((String) value);
			} else if (BeneficiarioIsentoColType.VALOR_ISENCAO.equals(beneficiarioIsentoColType)) {
				beneficiarioIsentoUi.setValorIsencao((BigDecimal) value);
			} else if (BeneficiarioIsentoColType.NR_MATRICULA_EMPRESA.equals(beneficiarioIsentoColType)) {
				beneficiarioIsentoUi.setMatriculaEmpresa((Long) value);
			} else if (BeneficiarioIsentoColType.DT_INICIO.equals(beneficiarioIsentoColType)) {
				beneficiarioIsentoUi.setDtInicio((LocalDate) value);
			} else if (BeneficiarioIsentoColType.DT_FIM.equals(beneficiarioIsentoColType)) {
				beneficiarioIsentoUi.setDtFim((LocalDate) value);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public Object getBeneficiarioIsentoValue(
			BeneficiarioIsentoColType beneficiarioIsentoColType,
			BeneficiarioIsentoUi beneficiarioIsentoUi) throws ServiceException {
		Object value = null;

		try {
			LOGGER.info("BEGIN");

			if (BeneficiarioIsentoColType.NR_MATRICULA.equals(beneficiarioIsentoColType)) {
				value = beneficiarioIsentoUi.getMatricula();
			} else if (BeneficiarioIsentoColType.NR_CPF.equals(beneficiarioIsentoColType)) {
				value = beneficiarioIsentoUi.getCpf();
			} else if (BeneficiarioIsentoColType.NM_BENEFICIARIO.equals(beneficiarioIsentoColType)) {
				value = beneficiarioIsentoUi.getName();
			} else if (BeneficiarioIsentoColType.DT_NASCIMENTO.equals(beneficiarioIsentoColType)) {
				value = beneficiarioIsentoUi.getDtNascimento();
			} else if (BeneficiarioIsentoColType.TP_ISENTO.equals(beneficiarioIsentoColType)) {
				value = beneficiarioIsentoUi.getIsentoType();
			} else if (BeneficiarioIsentoColType.NR_MATRICULA_TITULAR.equals(beneficiarioIsentoColType)) {
				value = beneficiarioIsentoUi.getMatriculaTitular();
			} else if (BeneficiarioIsentoColType.NM_TITULAR.equals(beneficiarioIsentoColType)) {
				value = beneficiarioIsentoUi.getNameTitular();
			} else if (BeneficiarioIsentoColType.VALOR_ISENCAO.equals(beneficiarioIsentoColType)) {
				value = beneficiarioIsentoUi.getValorIsencao();
			} else if (BeneficiarioIsentoColType.NR_MATRICULA_EMPRESA.equals(beneficiarioIsentoColType)) {
				value = beneficiarioIsentoUi.getMatriculaEmpresa();
			}

			LOGGER.info("END");
			return value;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void saveIsentos(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			LOGGER.info(
					"Saving [{}] registers of TitularIsento:",
					coParticipacaoContext.getBunker().getTitularIsentoUis().size());
			titularIsentoBatchService.saveBatch(coParticipacaoContext.getBunker().getTitularIsentoUis());

			LOGGER.info(
					"Saving [{}] registers of DependenteIsento:",
					coParticipacaoContext.getBunker().getDependenteIsentoUis().size());
			dependenteIsentoBatchService.saveBatch(coParticipacaoContext.getBunker().getDependenteIsentoUis());

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

	public void deleteByMesAndAno(EmpresaUi empresaUi, Integer mes, Integer ano) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			LOGGER.info("Removing all Isentos in this month:");
			dependenteIsentoService.deleteByMesAndAno(empresaUi, mes, ano);
			titularIsentoService.deleteByMesAndAno(empresaUi, mes, ano);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void beforeProcess(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			deleteByContratoAndMesAndAno(
					coParticipacaoContext.getContratoUi(),
					coParticipacaoContext.getMes(),
					coParticipacaoContext.getAno());

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void deleteByContratoAndMesAndAno(ContratoUi contratoUi, Integer mes, Integer ano) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			LOGGER.info("Removing all Isentos in this month:");
			dependenteIsentoService.deleteByContratoAndMesAndAno(contratoUi, mes, ano);
			titularIsentoService.deleteByContratoAndMesAndAno(contratoUi, mes, ano);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional(value = AbstractService.JDBC_TX, propagation = Propagation.REQUIRED)
	public void afterProcess(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Process ending and sending data to database:");

			if (coParticipacaoContext.getEmpresaUi().isCreateBeneficiarioFromIsento()) {
				LOGGER.info("Creating Beneficiarios that we dont have at database:");

				titularBatchService.saveBatch(coParticipacaoContext, coParticipacaoContext.getBunker().getTitularUis());
				dependenteBatchService
						.saveBatch(coParticipacaoContext, coParticipacaoContext.getBunker().getDependenteUis());
			}

			LOGGER.info("Storing Isentos to database:");
			saveIsentos(coParticipacaoContext);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public boolean validateLine(String line, CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		return true;
	}

	public boolean validateSheet(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		List<IsentoInputSheetCols> isentoInputSheetCols;

		try {
			LOGGER.info("BEGIN");

			/*
			 * Deve ser usado apenas se todas as pastas da planilha forem
			 * iguais, habilitando este valor o processo irá ler TODAS as pastas
			 * existentes na planilha de Isentos:
			 */
			if (Boolean.TRUE.equals(coParticipacaoContext.getContratoUi().isSpreadsheetAllPages())) {
				LOGGER.debug("Reading sheet [{}]:", coParticipacaoContext.getCurrentSheet());
				return true;
			} else {
				isentoInputSheetCols = coParticipacaoContext
						.listIsentoInputSheetColsBySheetId(coParticipacaoContext.getCurrentSheet());

				if (!isentoInputSheetCols.isEmpty()) {
					LOGGER.debug("Reading sheet [{}]:", coParticipacaoContext.getCurrentSheet());
					return true;
				}
			}

			LOGGER.debug("Ignoring sheet [{}]:", coParticipacaoContext.getCurrentSheet());
			LOGGER.info("END");
			return false;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
