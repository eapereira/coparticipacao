package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter;

import javax.persistence.AttributeConverter;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class YesOrNotConverter implements AttributeConverter<Boolean, String> {

	private static final String TRUE = "S";
	private static final String FALSE = "N";

	@Override
	public String convertToDatabaseColumn(Boolean value) {
		if (value != null) {
			if (Boolean.TRUE.equals(value)) {
				return TRUE;
			} else {
				return FALSE;
			}
		}

		return null;
	}

	@Override
	public Boolean convertToEntityAttribute(String value) {
		if (StringUtils.isNotBlank(value)) {
			if (TRUE.equals(value)) {
				return Boolean.TRUE;
			}
		}

		return null;
	}
}
