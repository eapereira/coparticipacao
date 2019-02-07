package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputSheetCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ValorType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoInputSheetColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegisterColumnUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class LancamentoDetailServiceImpl implements LancamentoDetailService {

	private static final Logger LOGGER = LogManager.getLogger(LancamentoDetailServiceImpl.class);

	public void setFieldValue(LancamentoDetailUi lancamentoDetailUi, LancamentoColType lancamentoColType, Object value)
			throws ServiceException {
		LocalDate dtMovimento;

		try {
			LOGGER.info("BEGIN");

			if (value != null) {
				if (LancamentoColType.CD_MES.equals(lancamentoColType)) {
					lancamentoDetailUi.setMes((Integer) value);
				} else if (LancamentoColType.CD_ANO.equals(lancamentoColType)) {
					lancamentoDetailUi.setAno((Integer) value);
				} else if (LancamentoColType.DT_MOVIMENTO.equals(lancamentoColType)) {
					dtMovimento = (LocalDate) value;

					lancamentoDetailUi.setDtMovimento(dtMovimento);
					lancamentoDetailUi.setMes(dtMovimento.getMonthValue());
					lancamentoDetailUi.setAno(dtMovimento.getYear());
				} else if (LancamentoColType.ID_CONTRATO.equals(lancamentoColType)) {
					lancamentoDetailUi.setCdContrato((String) value);
				} else if (LancamentoColType.NR_CPF.equals(lancamentoColType)) {
					lancamentoDetailUi.setCpf((Long) value);
				} else if (LancamentoColType.NR_MATRICULA_DEPENDENTE.equals(lancamentoColType)) {
					lancamentoDetailUi.setMatriculaDependente((Long) value);
				} else if (LancamentoColType.NR_MATRICULA_TITULAR.equals(lancamentoColType)) {
					lancamentoDetailUi.setMatriculaTitular((Long) value);
				} else if (LancamentoColType.VL_PRINCIPAL.equals(lancamentoColType)) {
					lancamentoDetailUi.setValorPrincipal((BigDecimal) value);
				} else if (LancamentoColType.TP_VALOR.equals(lancamentoColType)) {
					lancamentoDetailUi.setValorType(ValorType.parse((String) value));
				} else if (LancamentoColType.NM_BENEFICIARIO.equals(lancamentoColType)) {
					lancamentoDetailUi.setNameBeneficiario((String) value);
				} else if (LancamentoColType.NM_TITULAR.equals(lancamentoColType)) {
					lancamentoDetailUi.setNameTitular((String) value);
				} else if (LancamentoColType.DT_NASCIMENTO.equals(lancamentoColType)) {
					lancamentoDetailUi.setDtNascimento((LocalDate) value);
				} else if (LancamentoColType.VL_REEMBOLSO.equals(lancamentoColType)) {
					lancamentoDetailUi.setValorReembolso((BigDecimal) value);
				} else if (LancamentoColType.VL_PARTICIPACAO.equals(lancamentoColType)) {
					lancamentoDetailUi.setValorParticipacao((BigDecimal) value);
				} else if (LancamentoColType.CD_USUARIO.equals(lancamentoColType)) {
					lancamentoDetailUi.setCdUsuario((String) value);
				} else if (LancamentoColType.DT_UTILIZACAO.equals(lancamentoColType)) {
					lancamentoDetailUi.setDtUtilizacao((LocalDate) value);
				} else if (LancamentoColType.NR_SUBFATURA.equals(lancamentoColType)) {
					lancamentoDetailUi.setSubFatura((Integer) value);
				} else if (LancamentoColType.DESCR_UTILIZACAO.equals(lancamentoColType)) {
					lancamentoDetailUi.setDescrUtilizacao((String) value);
				} else if (LancamentoColType.NR_MATRICULA_ESPECIAL.equals(lancamentoColType)) {
					lancamentoDetailUi.setMatriculaEspecial((String) value);
				} else if (LancamentoColType.NR_LOCAL.equals(lancamentoColType)) {
					lancamentoDetailUi.setLocal((Integer) value);
				} else if (LancamentoColType.NR_MATRICULA_EMPRESA.equals(lancamentoColType)) {
					lancamentoDetailUi.setMatriculaEmpresa((Long) value);
				} else {
					throw new ServiceException("The column LancamentoInputColsUi[{}] is not recognized:");
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public Object getFieldValue(LancamentoDetailUi lancamentoDetailUi, LancamentoColType lancamentoColType)
			throws ServiceException {
		Object value;

		try {
			LOGGER.info("BEGIN");

			value = null;

			if (LancamentoColType.CD_MES.equals(lancamentoColType)) {
				value = lancamentoDetailUi.getMes();
			} else if (LancamentoColType.CD_ANO.equals(lancamentoColType)) {
				value = lancamentoDetailUi.getAno();
			} else if (LancamentoColType.DT_MOVIMENTO.equals(lancamentoColType)) {
				value = lancamentoDetailUi.getDtMovimento();
			} else if (LancamentoColType.ID_CONTRATO.equals(lancamentoColType)) {
				value = lancamentoDetailUi.getContratoUi();
			} else if (LancamentoColType.NR_CPF.equals(lancamentoColType)) {
				value = lancamentoDetailUi.getCpf();
			} else if (LancamentoColType.NR_MATRICULA_DEPENDENTE.equals(lancamentoColType)) {
				value = lancamentoDetailUi.getMatriculaDependente();
			} else if (LancamentoColType.NR_MATRICULA_TITULAR.equals(lancamentoColType)) {
				value = lancamentoDetailUi.getMatriculaTitular();
			} else if (LancamentoColType.VL_PRINCIPAL.equals(lancamentoColType)) {
				value = lancamentoDetailUi.getValorPrincipal();
			} else if (LancamentoColType.TP_VALOR.equals(lancamentoColType)) {
				value = lancamentoDetailUi.getValorType();
			} else if (LancamentoColType.NM_BENEFICIARIO.equals(lancamentoColType)) {
				value = lancamentoDetailUi.getNameBeneficiario();
			} else if (LancamentoColType.NM_TITULAR.equals(lancamentoColType)) {
				value = lancamentoDetailUi.getNameTitular();
			} else if (LancamentoColType.DT_NASCIMENTO.equals(lancamentoColType)) {
				value = lancamentoDetailUi.getDtNascimento();
			} else if (LancamentoColType.VL_REEMBOLSO.equals(lancamentoColType)) {
				value = lancamentoDetailUi.getValorReembolso();
			} else if (LancamentoColType.VL_PARTICIPACAO.equals(lancamentoColType)) {
				value = lancamentoDetailUi.getValorParticipacao();
			} else if (LancamentoColType.CD_USUARIO.equals(lancamentoColType)) {
				value = lancamentoDetailUi.getCdUsuario();
			} else if (LancamentoColType.DT_UTILIZACAO.equals(lancamentoColType)) {
				value = lancamentoDetailUi.getDtUtilizacao();
			} else if (LancamentoColType.NR_SUBFATURA.equals(lancamentoColType)) {
				value = lancamentoDetailUi.getSubFatura();
			} else {
				throw new ServiceException("The column LancamentoInputColsUi[{}] is not recognized:");
			}

			LOGGER.info("END");
			return value;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public BigDecimal getFieldValueAsBigDecimal(
			LancamentoInputSheetColsUi lancamentoInputColsUi,
			LancamentoDetailUi lancamentoDetailUi) throws ServiceException {
		BigDecimal value;
		LancamentoColType lancamentoColType;

		try {
			LOGGER.info("BEGIN");

			lancamentoColType = lancamentoInputColsUi.getLancamentoColType();

			if (LancamentoColType.NR_MATRICULA_DEPENDENTE.equals(lancamentoColType)
					|| LancamentoColType.NR_MATRICULA_TITULAR.equals(lancamentoColType)) {
				value = BigDecimal.valueOf(
						(Long) getFieldValue(lancamentoDetailUi, lancamentoInputColsUi.getLancamentoColType()));
			} else if (LancamentoColType.VL_PRINCIPAL.equals(lancamentoColType)) {
				value = (BigDecimal) getFieldValue(lancamentoDetailUi, lancamentoInputColsUi.getLancamentoColType());
			} else {
				throw new ServiceException("The column LancamentoInputColsUi[{}] cannot be used as BigDecimal:");
			}

			LOGGER.info("END");
			return value;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public LancamentoInputSheetColsUi findByRegisterColumnId(
			CoParticipacaoContext coParticipacaoContext,
			RegisterColumnUi arquivoInputColsDefUi) throws ServiceException {
		List<LancamentoInputSheetCols> lancamentoInputSheetColss;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Searching for column[{}] in LancamentoInputCols:", arquivoInputColsDefUi.getNameColumn());

			lancamentoInputSheetColss = coParticipacaoContext
					.listLancamentoInputSheetColsBySheetId(coParticipacaoContext.getCurrentSheet());

			for (LancamentoInputSheetCols lancamentoInputSheetCols : lancamentoInputSheetColss) {
				if (lancamentoInputSheetCols.getRegisterColumn().getId().equals(arquivoInputColsDefUi.getId())) {
					LOGGER.info("END");
					return (LancamentoInputSheetColsUi) lancamentoInputSheetCols;
				}
			}

			LOGGER.info("END");
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void showLancamentoDetailInfo(LancamentoDetailUi lancamentoDetailUi) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			LOGGER.info("LancamentoDetailUi.CD_MES: ..................... [{}]", lancamentoDetailUi.getMes());
			LOGGER.info("LancamentoDetailUi.CD_ANO: ..................... [{}]", lancamentoDetailUi.getAno());
			LOGGER.info("LancamentoDetailUi.DT_MOVTO: ................... [{}]", lancamentoDetailUi.getDtMovimento());
			LOGGER.info(
					"LancamentoDetailUi.NR_MATRICULA_TITULAR: ....... [{}]",
					lancamentoDetailUi.getMatriculaTitular());
			LOGGER.info(
					"LancamentoDetailUi.NR_MATRICULA_DEPENDENTE: .... [{}]",
					lancamentoDetailUi.getMatriculaDependente());
			LOGGER.info("LancamentoDetailUi.NR_CPF: ..................... [{}]", lancamentoDetailUi.getCpf());
			LOGGER.info(
					"LancamentoDetailUi.NM_BENEFICIARIO: ............ [{}]",
					lancamentoDetailUi.getNameBeneficiario());
			LOGGER.info("LancamentoDetailUi.NM_TITULAR: ................. [{}]", lancamentoDetailUi.getNameTitular());
			LOGGER.info(
					"LancamentoDetailUi.VL_PRINCIPAL: ............... [{}]",
					lancamentoDetailUi.getValorPrincipal());

			if (lancamentoDetailUi.getValorType() != null) {
				LOGGER.info(
						"LancamentoDetailUi.TP_VALOR: ................... [{}]",
						lancamentoDetailUi.getValorType().getDescription());
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public LancamentoDetailUi create(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		LancamentoDetailUi lancamentoDetailUi;
		List<LancamentoInputSheetCols> lancamentoInputSheetCols;
		BigDecimal valorPrincipal;

		try {
			LOGGER.info("BEGIN");

			lancamentoInputSheetCols = coParticipacaoContext
					.listLancamentoInputSheetColsBySheetId(coParticipacaoContext.getCurrentSheet());

			if (!lancamentoInputSheetCols.isEmpty()) {
				lancamentoDetailUi = createFromLancamentoInput(coParticipacaoContext, lancamentoInputSheetCols);
			} else {
				LOGGER.info(
						"There's no registers in LancamentoInputCols mapping to ArquivoInput, so we can read and store Lancamentos:");

				throw new ServiceException(
						"There's no registers in LancamentoInputCols mapping to ArquivoInput, so we can read and store Lancamentos:");
			}

			if (lancamentoDetailUi.getMes() == null) {
				lancamentoDetailUi.setMes(coParticipacaoContext.getMes());
			} else {
				coParticipacaoContext.setMes(lancamentoDetailUi.getMes());
			}

			if (lancamentoDetailUi.getAno() == null) {
				lancamentoDetailUi.setAno(coParticipacaoContext.getAno());
			} else {
				coParticipacaoContext.setAno(lancamentoDetailUi.getAno());
			}

			if (lancamentoDetailUi.getContratoUi() == null) {
				lancamentoDetailUi.setContratoUi(coParticipacaoContext.getContratoUi());
				lancamentoDetailUi.setCdContrato(coParticipacaoContext.getContratoUi().getCdContrato());
			}

			coParticipacaoContext.setLancamentoDetailUi(lancamentoDetailUi);

			if (lancamentoDetailUi.getValorType() != null) {
				if (ValorType.NEGATIVO.equals(lancamentoDetailUi.getValorType())) {
					valorPrincipal = lancamentoDetailUi.getValorPrincipal()
							.multiply(BigDecimal.valueOf(NumberUtils.INTEGER_MINUS_ONE));
					lancamentoDetailUi.setValorPrincipal(valorPrincipal);
				}
			}

			LOGGER.info("END");
			return lancamentoDetailUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private LancamentoDetailUi createFromLancamentoInput(
			CoParticipacaoContext coParticipacaoContext,
			List<LancamentoInputSheetCols> lancamentoInputSheetColss) throws ServiceException {
		LancamentoDetailUi lancamentoDetailUi;
		RegisterColumnUi registerColumnUi;
		Object value;

		try {
			LOGGER.info("BEGIN");

			lancamentoDetailUi = new LancamentoDetailUi();

			for (LancamentoInputSheetCols lancamentoInputSheetCols : lancamentoInputSheetColss) {
				// Processando uma linha do arquivo:
				LOGGER.info(
						"Using LancamentoInputCols[{}]",
						lancamentoInputSheetCols.getLancamentoColType().getDescription());

				registerColumnUi = (RegisterColumnUi) lancamentoInputSheetCols.getRegisterColumn();

				value = coParticipacaoContext.getMapLine().get(registerColumnUi.getNameColumn());

				LOGGER.debug("Column [{}] with value [{}]:", registerColumnUi.getNameColumn(), value);

				setFieldValue(lancamentoDetailUi, lancamentoInputSheetCols.getLancamentoColType(), value);
			}

			LOGGER.info("END");
			return lancamentoDetailUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
