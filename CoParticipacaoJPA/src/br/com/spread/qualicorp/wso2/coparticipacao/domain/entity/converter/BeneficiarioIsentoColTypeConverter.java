package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter;

import javax.persistence.AttributeConverter;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioIsentoColType;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class BeneficiarioIsentoColTypeConverter implements AttributeConverter<BeneficiarioIsentoColType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(BeneficiarioIsentoColType value) {
		if (value != null) {
			return value.getId();
		}

		return null;
	}

	@Override
	public BeneficiarioIsentoColType convertToEntityAttribute(Integer value) {
		if (value != null) {
			return BeneficiarioIsentoColType.parse(value);
		}

		return null;
	}

}
