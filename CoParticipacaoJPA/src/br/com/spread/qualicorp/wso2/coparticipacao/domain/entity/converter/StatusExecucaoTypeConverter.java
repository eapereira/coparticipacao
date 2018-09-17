package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter;

import javax.persistence.AttributeConverter;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.StatusExecucaoType;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class StatusExecucaoTypeConverter implements AttributeConverter<StatusExecucaoType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(StatusExecucaoType value) {
		if (value != null) {
			return value.getId();
		}

		return null;
	}

	@Override
	public StatusExecucaoType convertToEntityAttribute(Integer value) {
		if (value != null) {
			return StatusExecucaoType.parse(value);
		}

		return null;
	}

}
