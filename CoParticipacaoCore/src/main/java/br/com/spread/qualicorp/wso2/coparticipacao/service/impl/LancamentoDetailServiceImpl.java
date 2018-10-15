package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ValorType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoDetailUi;
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

	public void setFieldValue(
			LancamentoDetailUi lancamentoDetailUi,
			LancamentoInputColsUi lancamentoInputColsUi,
			Object value) throws ServiceException {
		LancamentoColType lancamentoColType;
		LocalDate dtMovimento;

		try {
			LOGGER.info("BEGIN");

			lancamentoColType = lancamentoInputColsUi.getLancamentoColType();

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
				// lancamentoDetailUi.setContratoUi((ContratoUi) value);
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
			} else {
				throw new ServiceException("The column LancamentoInputColsUi[{}] is not recognized:");
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public Object getFieldValue(LancamentoDetailUi lancamentoDetailUi, LancamentoInputColsUi lancamentoInputColsUi)
			throws ServiceException {
		Object value;
		LancamentoColType lancamentoColType;

		try {
			LOGGER.info("BEGIN");

			value = null;
			lancamentoColType = lancamentoInputColsUi.getLancamentoColType();

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
				value = BigDecimal.valueOf((Long) getFieldValue(lancamentoDetailUi, lancamentoInputColsUi));
			} else if (LancamentoColType.VL_PRINCIPAL.equals(lancamentoColType)) {
				value = (BigDecimal) getFieldValue(lancamentoDetailUi, lancamentoInputColsUi);
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

}
