package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter;

import javax.persistence.AttributeConverter;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.SexoType;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class SexoTypeConverter implements AttributeConverter<SexoType, String> {

	@Override
	public String convertToDatabaseColumn(SexoType value) {
		if (value != null) {
			return value.getId();
		}

		return null;
	}

	@Override
	public SexoType convertToEntityAttribute(String value) {
		if (value != null) {
			return SexoType.parse(value);
		}

		return null;
	}
}
