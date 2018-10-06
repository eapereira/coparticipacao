package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter;

import javax.persistence.AttributeConverter;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ExecucaoType;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class ExecucaoTypeConverter implements AttributeConverter<ExecucaoType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(ExecucaoType value) {
		if (value != null) {
			return value.getId();
		}

		return null;
	}

	@Override
	public ExecucaoType convertToEntityAttribute(Integer value) {
		if (value != null) {
			return ExecucaoType.parse(value);
		}

		return null;
	}

}
