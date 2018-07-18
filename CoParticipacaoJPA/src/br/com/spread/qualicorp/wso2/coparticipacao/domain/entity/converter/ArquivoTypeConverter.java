package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter;

import javax.persistence.AttributeConverter;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoType;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class ArquivoTypeConverter
		implements AttributeConverter<ArquivoType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(ArquivoType value) {
		if (value != null) {
			return value.getId();
		}

		return null;
	}

	@Override
	public ArquivoType convertToEntityAttribute(Integer value) {
		if (value != null) {
			return ArquivoType.parse(value);
		}

		return null;
	}

}
