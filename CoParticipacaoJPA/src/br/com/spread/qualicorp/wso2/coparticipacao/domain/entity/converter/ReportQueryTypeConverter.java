package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter;

import javax.persistence.AttributeConverter;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ReportQueryType;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class ReportQueryTypeConverter implements AttributeConverter<ReportQueryType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(ReportQueryType value) {
		if (value != null) {
			return value.getId();
		}

		return null;
	}

	@Override
	public ReportQueryType convertToEntityAttribute(Integer value) {
		if (value != null) {
			return ReportQueryType.parse(value);
		}

		return null;
	}

}
