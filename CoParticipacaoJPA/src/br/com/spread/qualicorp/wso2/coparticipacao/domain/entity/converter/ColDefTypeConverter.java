package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter;

import javax.persistence.AttributeConverter;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class ColDefTypeConverter
		implements
		AttributeConverter<ColDefType, Integer> {

	public Integer convertToDatabaseColumn(ColDefType value) {
		if (value != null) {
			return value.getId();
		}

		return null;
	}

	public ColDefType convertToEntityAttribute(Integer value) {
		if (value != null) {
			return ColDefType.parse(value);
		}

		return null;
	}

}
