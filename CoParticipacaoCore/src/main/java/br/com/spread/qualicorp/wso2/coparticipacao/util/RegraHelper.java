package br.com.spread.qualicorp.wso2.coparticipacao.util;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegisterColumnUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class RegraHelper {
	private static final Logger LOGGER = LogManager.getLogger(RegraHelper.class);

	public static void setFieldValueAsBigDecimal(
			CoParticipacaoContext coParticipacaoContext,
			RegisterColumnUi RegisterColumnUi,
			BigDecimal result) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (ColDefType.DOUBLE.equals(RegisterColumnUi.getType())) {
				coParticipacaoContext.setColumnValue(RegisterColumnUi, result);
			} else if (ColDefType.LONG.equals(RegisterColumnUi.getType())) {
				coParticipacaoContext.setColumnValue(RegisterColumnUi, result.longValue());
			} else if (ColDefType.INT.equals(RegisterColumnUi.getType())) {
				coParticipacaoContext.setColumnValue(RegisterColumnUi, result.intValue());
			} else {
				throw new ServiceException(
						"The column[%s] must be a number type to use in RegraService:",
						RegisterColumnUi.getNameColumn());
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public static Object getFieldValue(CoParticipacaoContext coParticipacaoContext, RegisterColumnUi RegisterColumnUi)
			throws ServiceException {
		Object value = null;

		try {
			LOGGER.info("BEGIN");

			if (ColDefType.DOUBLE.equals(RegisterColumnUi.getType())) {
				value = (BigDecimal) coParticipacaoContext.getColumnValue(RegisterColumnUi);
			} else if (ColDefType.LONG.equals(RegisterColumnUi.getType())) {
				value = BigDecimal.valueOf((Long) coParticipacaoContext.getColumnValue(RegisterColumnUi));
			} else if (ColDefType.INT.equals(RegisterColumnUi.getType())) {
				value = BigDecimal.valueOf((Integer) coParticipacaoContext.getColumnValue(RegisterColumnUi));
			} else if (ColDefType.STRING.equals(RegisterColumnUi.getType())) {
				value = (String) coParticipacaoContext.getColumnValue(RegisterColumnUi);
			} else {
				throw new ServiceException(
						"The column[%s] must be a number type to use in RegraService:",
						RegisterColumnUi.getNameColumn());
			}

			LOGGER.info("END");
			return value;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
