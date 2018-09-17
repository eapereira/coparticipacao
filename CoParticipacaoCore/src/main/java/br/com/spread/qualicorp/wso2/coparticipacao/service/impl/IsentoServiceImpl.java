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

import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioIsentoColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.IsentoInputSheetCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.IsentoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.IsentoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.io.impl.SpreadsheetProcessorListener;
import br.com.spread.qualicorp.wso2.coparticipacao.service.AbstractService;
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
@Transactional(value = AbstractService.JDBC_TX)
public class IsentoServiceImpl implements IsentoService, SpreadsheetProcessorListener {

	private static final Logger LOGGER = LogManager.getLogger(IsentoServiceImpl.class);

	@Autowired
	private DependenteIsentoService dependenteIsentoService;

	@Autowired
	private TitularIsentoService titularIsentoService;

	@Autowired
	private RegraService regraService;

	public boolean hasIsento(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		ArquivoInputUi arquivoInputUi;

		try {
			LOGGER.info("BEGIN");

			arquivoInputUi = coParticipacaoContext.getArquivoInputUi();

			if (!arquivoInputUi.getIsentoInputSheets().isEmpty()) {
				return true;
			}

			LOGGER.info("END");
			return false;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void processIsento(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		TitularUi titularUi = null;
		List<IsentoInputSheetUi> isentoInputSheetUis;
		List<IsentoInputSheetCols> isentoInputSheetCols;
		BeneficiarioIsentoUi beneficiarioIsentoUi = null;
		DependenteUi dependenteUi = null;
		IsentoType isentoType = null;

		try {
			LOGGER.info("BEGIN");

			isentoInputSheetUis = coParticipacaoContext.getIsentoInputSheetUis();

			for (IsentoInputSheetUi isentoInputSheetUi : isentoInputSheetUis) {
				if (coParticipacaoContext.getCurrentSheet().equals(isentoInputSheetUi.getSheetId())) {
					isentoInputSheetCols = isentoInputSheetUi.getIsentoInputSheetCols();
					isentoType = isentoInputSheetUi.getIsentoType();

					beneficiarioIsentoUi = createBeneficiarioIsento(coParticipacaoContext, isentoInputSheetCols);
					regraService.applyRegras(coParticipacaoContext, beneficiarioIsentoUi, isentoInputSheetCols);
					break;
				}
			}

			if (beneficiarioIsentoUi != null) {
				if (beneficiarioIsentoUi.getCpf() != null) {
					titularUi = coParticipacaoContext
							.findTitularByCpfAndName(beneficiarioIsentoUi.getCpf(), beneficiarioIsentoUi.getName());
				}

				if (titularUi == null) {
					titularUi = coParticipacaoContext.findTitularByMatriculaAndName(
							beneficiarioIsentoUi.getMatricula(),
							beneficiarioIsentoUi.getName());
				}

				if (titularUi != null) {
					createTitularIsento(titularUi, beneficiarioIsentoUi, isentoType, coParticipacaoContext);
				} else {
					LOGGER.info(
							"BeneficiárioIsento [{}] user of CPF[{}] is not a titular:",
							beneficiarioIsentoUi.getName(),
							beneficiarioIsentoUi.getCpf());

					if (beneficiarioIsentoUi.getCpf() != null) {
						dependenteUi = coParticipacaoContext.findDependenteByCpfAndName(
								beneficiarioIsentoUi.getCpf(),
								beneficiarioIsentoUi.getName());
					}

					if (dependenteUi == null) {
						dependenteUi = coParticipacaoContext.findDependenteByMatriculaAndName(
								beneficiarioIsentoUi.getMatricula(),
								beneficiarioIsentoUi.getName());

						if (dependenteUi == null) {
							dependenteUi = coParticipacaoContext.findDependenteByMatriculaAndName(
									beneficiarioIsentoUi.getMatriculaTitular(),
									beneficiarioIsentoUi.getName());
						}
					}

					if (dependenteUi != null) {
						createDependenteIsento(dependenteUi, beneficiarioIsentoUi, isentoType, coParticipacaoContext);
					} else {
						LOGGER.info(
								"BeneficiárioIsento [{}] user of CPF[{}] is not a Dependente:",
								beneficiarioIsentoUi.getName(),
								beneficiarioIsentoUi.getCpf());
						LOGGER.info("Beneficiario not exists in database:");
					}
				}
			} else {
				LOGGER.info(
						"Couldn't find a suitable BeneficiarioIsentoInputCols to convert row data into BeneficiarioIsento.");
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void createDependenteIsento(
			DependenteUi dependenteUi,
			BeneficiarioIsentoUi beneficiarioIsentoUi,
			IsentoType isentoType,
			CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		DependenteIsentoUi dependenteIsentoUi;

		try {
			LOGGER.info("BEGIN");

			dependenteIsentoUi = new DependenteIsentoUi();
			dependenteIsentoUi.setDependente(dependenteUi);

			if (isentoType != null) {
				dependenteIsentoUi.setIsentoType(isentoType);
			} else {
				dependenteIsentoUi.setIsentoType(beneficiarioIsentoUi.getIsentoType());
			}

			dependenteIsentoUi.setContrato(coParticipacaoContext.getContratoUi());
			dependenteIsentoUi.setMes(coParticipacaoContext.getMes());
			dependenteIsentoUi.setAno(coParticipacaoContext.getAno());
			dependenteIsentoUi.setUserCreated(coParticipacaoContext.getUser());
			dependenteIsentoUi.setUserAltered(coParticipacaoContext.getUser());

			coParticipacaoContext.addDependenteIsento(dependenteIsentoUi);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void createTitularIsento(
			TitularUi titularUi,
			BeneficiarioIsentoUi beneficiarioIsentoUi,
			IsentoType isentoType,
			CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		TitularIsentoUi titularIsentoUi;

		try {
			LOGGER.info("BEGIN");

			titularIsentoUi = new TitularIsentoUi();

			titularIsentoUi.setTitular(titularUi);

			if (isentoType != null) {
				titularIsentoUi.setIsentoType(isentoType);
			} else {
				titularIsentoUi.setIsentoType(beneficiarioIsentoUi.getIsentoType());
			}

			titularIsentoUi.setContrato(coParticipacaoContext.getContratoUi());
			titularIsentoUi.setMes(coParticipacaoContext.getMes());
			titularIsentoUi.setAno(coParticipacaoContext.getAno());
			titularIsentoUi.setValorIsencao(beneficiarioIsentoUi.getValorIsencao());

			titularIsentoUi.setUserCreated(coParticipacaoContext.getUser());
			titularIsentoUi.setUserAltered(coParticipacaoContext.getUser());

			coParticipacaoContext.addTitularIsento(titularIsentoUi);

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
					value = coParticipacaoContext.getColumnValue(
							(ArquivoInputColsDefUi) beneficiarioIsentoInputCol.getArquivoInputColsDef());

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
					beneficiarioIsentoUi.setIsentoType(IsentoType.parse((String) value));
				} else {
					beneficiarioIsentoUi.setIsentoType(IsentoType.parse((Integer) value));
				}
			} else if (BeneficiarioIsentoColType.NR_MATRICULA_TITULAR.equals(beneficiarioIsentoColType)) {
				beneficiarioIsentoUi.setMatriculaTitular((Long) value);
			} else if (BeneficiarioIsentoColType.NM_TITULAR.equals(beneficiarioIsentoColType)) {
				beneficiarioIsentoUi.setNameTitular((String) value);
			} else if (BeneficiarioIsentoColType.VALOR_ISENCAO.equals(beneficiarioIsentoColType)) {
				beneficiarioIsentoUi.setValorIsencao((BigDecimal) value);
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
			titularIsentoService.saveBatch(coParticipacaoContext.getBunker().getTitularIsentoUis());

			LOGGER.info(
					"Saving [{}] registers of DependenteIsento:",
					coParticipacaoContext.getBunker().getDependenteIsentoUis().size());
			dependenteIsentoService.saveBatch(coParticipacaoContext.getBunker().getDependenteIsentoUis());

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

	public void processLine(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Stating Isento processing:");
			processIsento(coParticipacaoContext);

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
		List<IsentoInputSheetUi> isentoInputSheetUis;

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
				isentoInputSheetUis = coParticipacaoContext.getIsentoInputSheetUis();

				for (IsentoInputSheetUi isentoInputSheetUi : isentoInputSheetUis) {
					if (isentoInputSheetUi.getSheetId().equals(coParticipacaoContext.getCurrentSheet())) {
						LOGGER.debug("Reading sheet [{}]:", coParticipacaoContext.getCurrentSheet());
						return true;
					}
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

	public BigDecimal getFieldValueAsBigDecimal(
			BeneficiarioIsentoColType beneficiarioIsentoColType,
			BeneficiarioIsentoUi beneficiarioIsentoUi) throws ServiceException {
		Object oValue = null;
		BigDecimal value = null;

		try {
			LOGGER.info("BEGIN");

			oValue = getBeneficiarioIsentoValue(beneficiarioIsentoColType, beneficiarioIsentoUi);

			LOGGER.debug(
					"Using field BeneficiarioIsento[{}] with value[{}]:",
					beneficiarioIsentoColType.getDescription(),
					value);

			if (oValue instanceof Long) {
				value = BigDecimal.valueOf((Long) oValue);
			} else if (oValue instanceof Double) {
				value = BigDecimal.valueOf((Double) oValue);
			} else {
				throw new ServiceException(
						"The field[%s] must be Long or Double to be used for Regra:",
						beneficiarioIsentoColType.getDescription());
			}

			LOGGER.info("END");
			return value;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void setFieldValueAsBigDecimal(
			BeneficiarioIsentoColType beneficiarioIsentoColType,
			BeneficiarioIsentoUi beneficiarioIsentoUi,
			BigDecimal value) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			LOGGER.debug(
					"Using field BeneficiarioIsento[{}] with value[{}]:",
					beneficiarioIsentoColType.getDescription(),
					value);

			if (BeneficiarioIsentoColType.VALOR_ISENCAO.equals(beneficiarioIsentoColType)) {
				setBeneficiarioIsentoValue(beneficiarioIsentoColType, beneficiarioIsentoUi, value);
			} else if (BeneficiarioIsentoColType.NR_MATRICULA.equals(beneficiarioIsentoColType)
					|| BeneficiarioIsentoColType.NR_MATRICULA_TITULAR.equals(beneficiarioIsentoColType)) {
				setBeneficiarioIsentoValue(beneficiarioIsentoColType, beneficiarioIsentoUi, value.longValue());
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
