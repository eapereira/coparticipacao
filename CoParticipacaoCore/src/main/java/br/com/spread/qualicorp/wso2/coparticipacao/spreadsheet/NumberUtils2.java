package br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet;

import java.text.DecimalFormat;
import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class NumberUtils2 {
	public static String zerosLeft(Integer value, int size) {
		if (value != null) {
			return StringUtils.leftPad(value.toString(), size, "0");
		}

		return StringUtils.EMPTY;
	}

	public static Double stringToDouble(String value, String pattern) throws CoParticipacaoException {
		DecimalFormat decimalFormat;

		try {
			if (StringUtils.isNotBlank(value)) {
				decimalFormat = new DecimalFormat(pattern);

				return decimalFormat.parse(value.trim()).doubleValue();
			}

			return NumberUtils.DOUBLE_ZERO;
		} catch (ParseException e) {
			throw new CoParticipacaoException(e);
		}
	}

	public static boolean isNotZero(Long value) {
		if (value != null) {
			if (!NumberUtils.LONG_ZERO.equals(value)) {
				return true;
			}
		}

		return false;
	}
}
