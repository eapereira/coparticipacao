package br.com.spread.qualicorp.wso2.coparticipacao.util;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetColsDefUi;
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
			ArquivoInputSheetColsDefUi arquivoInputSheetColsDefUi,
			BigDecimal result) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (ColDefType.DOUBLE.equals(arquivoInputSheetColsDefUi.getType())) {
				coParticipacaoContext.setColumnValue(arquivoInputSheetColsDefUi, result);
			} else if (ColDefType.LONG.equals(arquivoInputSheetColsDefUi.getType())) {
				coParticipacaoContext.setColumnValue(arquivoInputSheetColsDefUi, result.longValue());
			} else if (ColDefType.INT.equals(arquivoInputSheetColsDefUi.getType())) {
				coParticipacaoContext.setColumnValue(arquivoInputSheetColsDefUi, result.intValue());
			} else {
				throw new ServiceException(
						"The column[%s] must be a number type to use in RegraService:",
						arquivoInputSheetColsDefUi.getNameColumn());
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public static Object getFieldValue(
			CoParticipacaoContext coParticipacaoContext,
			ArquivoInputSheetColsDefUi arquivoInputSheetColsDefUi) throws ServiceException {
		Object value = null;

		try {
			LOGGER.info("BEGIN");

			if (ColDefType.DOUBLE.equals(arquivoInputSheetColsDefUi.getType())) {
				value = (BigDecimal) coParticipacaoContext.getColumnValue(arquivoInputSheetColsDefUi);
			} else if (ColDefType.LONG.equals(arquivoInputSheetColsDefUi.getType())) {
				value = BigDecimal.valueOf((Long) coParticipacaoContext.getColumnValue(arquivoInputSheetColsDefUi));
			} else if (ColDefType.INT.equals(arquivoInputSheetColsDefUi.getType())) {
				value = BigDecimal.valueOf((Integer) coParticipacaoContext.getColumnValue(arquivoInputSheetColsDefUi));
			} else if (ColDefType.STRING.equals(arquivoInputSheetColsDefUi.getType())) {
				value = (String) coParticipacaoContext.getColumnValue(arquivoInputSheetColsDefUi);
			} else {
				throw new ServiceException(
						"The column[%s] must be a number type to use in RegraService:",
						arquivoInputSheetColsDefUi.getNameColumn());
			}

			LOGGER.info("END");
			return value;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
