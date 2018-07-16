package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter;

import javax.persistence.AttributeConverter;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.OperationType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class OperationTypeConverter
		implements
		AttributeConverter<OperationType, Integer> {

	public Integer convertToDatabaseColumn(OperationType value) {
		if (value != null) {
			return value.getId();
		}

		return null;
	}

	public OperationType convertToEntityAttribute(Integer value) {
		if (value != null) {
			return OperationType.parse(value);
		}

		return null;
	}

}
