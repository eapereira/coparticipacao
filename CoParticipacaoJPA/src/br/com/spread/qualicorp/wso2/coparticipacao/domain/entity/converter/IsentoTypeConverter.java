package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter;

import javax.persistence.AttributeConverter;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.IsentoType;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class IsentoTypeConverter
		implements AttributeConverter<IsentoType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(IsentoType value) {
		if (value != null) {
			return value.getId();
		}

		return null;
	}

	@Override
	public IsentoType convertToEntityAttribute(Integer value) {
		if (value != null) {
			return IsentoType.parse(value);
		}

		return null;
	}

}
