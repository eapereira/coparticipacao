package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter;

import javax.persistence.AttributeConverter;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class UseTypeConverter implements AttributeConverter<UseType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(UseType value) {
		if (value != null) {
			return value.getId();
		}

		return null;
	}

	@Override
	public UseType convertToEntityAttribute(Integer value) {
		if (value != null) {
			return UseType.parse(value);
		}

		return null;
	}

}
