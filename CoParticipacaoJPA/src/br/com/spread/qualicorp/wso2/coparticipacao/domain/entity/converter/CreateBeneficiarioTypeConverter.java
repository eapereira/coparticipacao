package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter;

import javax.persistence.AttributeConverter;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CreateBeneficiarioType;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class CreateBeneficiarioTypeConverter implements AttributeConverter<CreateBeneficiarioType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(CreateBeneficiarioType value) {
		if (value != null) {
			return value.getId();
		}

		return null;
	}

	@Override
	public CreateBeneficiarioType convertToEntityAttribute(Integer value) {
		if (value != null) {
			return CreateBeneficiarioType.parse(value);
		}

		return null;
	}

}
