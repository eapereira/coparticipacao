package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter;

import javax.persistence.AttributeConverter;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.DesconhecidoColType;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class DesconhecidoColTypeConverter implements AttributeConverter<DesconhecidoColType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(DesconhecidoColType value) {
		if (value != null) {
			return value.getId();
		}

		return null;
	}

	@Override
	public DesconhecidoColType convertToEntityAttribute(Integer value) {
		if (value != null) {
			return DesconhecidoColType.parse(value);
		}

		return null;
	}

}
