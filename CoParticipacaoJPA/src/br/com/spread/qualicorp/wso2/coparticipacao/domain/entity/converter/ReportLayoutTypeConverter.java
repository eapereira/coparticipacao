package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter;

import javax.persistence.AttributeConverter;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ReportLayoutType;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class ReportLayoutTypeConverter implements AttributeConverter<ReportLayoutType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(ReportLayoutType value) {
		if (value != null) {
			return value.getId();
		}

		return null;
	}

	@Override
	public ReportLayoutType convertToEntityAttribute(Integer value) {
		if (value != null) {
			return ReportLayoutType.parse(value);
		}

		return null;
	}

}
