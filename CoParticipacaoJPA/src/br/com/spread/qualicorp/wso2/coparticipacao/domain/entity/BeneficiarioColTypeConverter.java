package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.AttributeConverter;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioColType;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class BeneficiarioColTypeConverter
		implements AttributeConverter<BeneficiarioColType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(BeneficiarioColType value) {
		if (value != null) {
			return value.getId();
		}

		return null;
	}

	@Override
	public BeneficiarioColType convertToEntityAttribute(Integer value) {
		if (value != null) {
			return BeneficiarioColType.parse(value);
		}

		return null;
	}

}
