package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputSheetCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ValorType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoInputSheetUi;
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
			LancamentoInputColsUi lancamentoInputColsUi,
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

	public LancamentoInputColsUi findByArquivoInputColsDefId(
			CoParticipacaoContext coParticipacaoContext,
			ArquivoInputColsDefUi arquivoInputColsDefUi) throws ServiceException {
		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Searching for column[{}] in LancamentoInputCols:", arquivoInputColsDefUi.getNameColumn());

			for (LancamentoInputColsUi lancamentoInputColsUi : coParticipacaoContext.getLancamentoInputColsUis()) {
				if (lancamentoInputColsUi.getArquivoInputColsDef().getId().equals(arquivoInputColsDefUi.getId())) {
					LOGGER.info("END");
					return lancamentoInputColsUi;
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

		try {
			LOGGER.info("BEGIN");

			lancamentoDetailUi = new LancamentoDetailUi();
			coParticipacaoContext.setLancamentoDetailUi(lancamentoDetailUi);

			if (!coParticipacaoContext.getLancamentoInputColsUis().isEmpty()) {
				lancamentoDetailUi = createFromLancamentoInput(
						coParticipacaoContext,
						coParticipacaoContext.getLancamentoInputColsUis());
			} else if (!coParticipacaoContext.getLancamentoInputSheetUis().isEmpty()) {
				lancamentoDetailUi = createFromLancamentoInputSheet(
						coParticipacaoContext,
						coParticipacaoContext.getLancamentoInputSheetUis());
			} else {
				LOGGER.info(
						"There's no registers in LancamentoInputCols mapping to ArquivoInput, so we can read and store Lancamentos:");

				throw new ServiceException(
						"There's no registers in LancamentoInputCols mapping to ArquivoInput, so we can read and store Lancamentos:");
			}

			coParticipacaoContext.setLancamentoDetailUi(lancamentoDetailUi);

			LOGGER.info("END");
			return lancamentoDetailUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private LancamentoDetailUi createFromLancamentoInput(
			CoParticipacaoContext coParticipacaoContext,
			List<LancamentoInputColsUi> lancamentoInputColsUis) throws ServiceException {
		LancamentoDetailUi lancamentoDetailUi;
		ArquivoInputColsDefUi arquivoInputColsDefUi;
		Object value;

		try {
			LOGGER.info("BEGIN");

			lancamentoDetailUi = new LancamentoDetailUi();

			// Processando uma linha do arquivo:
			for (LancamentoInputColsUi lancamentoInputColsUi : lancamentoInputColsUis) {
				LOGGER.info(
						"Using LancamentoInputCols[{}]",
						lancamentoInputColsUi.getLancamentoColType().getDescription());

				arquivoInputColsDefUi = (ArquivoInputColsDefUi) lancamentoInputColsUi.getArquivoInputColsDef();

				value = coParticipacaoContext.getMapLine().get(arquivoInputColsDefUi.getNameColumn());

				LOGGER.debug("Column [{}] with value [{}]:", arquivoInputColsDefUi.getNameColumn(), value);

				setFieldValue(lancamentoDetailUi, lancamentoInputColsUi.getLancamentoColType(), value);
			}

			LOGGER.info("END");
			return lancamentoDetailUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private LancamentoDetailUi createFromLancamentoInputSheet(
			CoParticipacaoContext coParticipacaoContext,
			List<LancamentoInputSheetUi> lancamentoInputSheetUis) throws ServiceException {
		LancamentoDetailUi lancamentoDetailUi;
		ArquivoInputSheetColsDefUi arquivoInputSheetColsDefUi;
		List<LancamentoInputSheetCols> lancamentoInputSheetColss;
		Object value;
		ArquivoInputSheetUi arquivoInputSheetUi;

		try {
			LOGGER.info("BEGIN");

			lancamentoDetailUi = new LancamentoDetailUi();

			for (LancamentoInputSheetUi lancamentoInputSheetUi : lancamentoInputSheetUis) {
				arquivoInputSheetUi = (ArquivoInputSheetUi) lancamentoInputSheetUi.getArquivoInputSheet();

				if (arquivoInputSheetUi.getSheetId().equals(coParticipacaoContext.getCurrentSheet())) {
					lancamentoInputSheetColss = lancamentoInputSheetUi.getLancamentoInputSheetCols();

					// Processando uma linha do arquivo:
					for (LancamentoInputSheetCols lancamentoInputSheetCols : lancamentoInputSheetColss) {
						LOGGER.info(
								"Using LancamentoInputCols[{}]",
								lancamentoInputSheetCols.getLancamentoColType().getDescription());

						arquivoInputSheetColsDefUi = (ArquivoInputSheetColsDefUi) lancamentoInputSheetCols
								.getArquivoInputSheetColsDef();

						value = coParticipacaoContext.getMapLine().get(arquivoInputSheetColsDefUi.getNameColumn());

						LOGGER.debug("Column [{}] with value [{}]:", arquivoInputSheetColsDefUi.getNameColumn(), value);

						setFieldValue(lancamentoDetailUi, lancamentoInputSheetCols.getLancamentoColType(), value);
					}
				}
			}

			LOGGER.info("END");
			return lancamentoDetailUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
