package br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class NumberUtils {
	public static String zerosLeft(Integer value, int size) {
		if (value != null) {
			return StringUtils.leftPad(value.toString(), size, "0");
		}

		return StringUtils.EMPTY;
	}
}
