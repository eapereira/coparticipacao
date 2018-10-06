package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter;

import javax.persistence.AttributeConverter;

import org.apache.commons.lang3.StringUtils;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.UFType;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class UFTypeConverter implements AttributeConverter<UFType, String> {

	@Override
	public String convertToDatabaseColumn(UFType value) {
		if (value != null) {
			return value.getId();
		}

		return null;
	}

	@Override
	public UFType convertToEntityAttribute(String value) {
		if (StringUtils.isNotBlank(value)) {
			return UFType.parse(value);
		}

		return null;
	}
}
