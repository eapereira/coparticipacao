package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import javax.persistence.AttributeConverter;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class DependenteTypeConverter
		implements
		AttributeConverter<BeneficiarioType, Integer> {

	public Integer convertToDatabaseColumn(BeneficiarioType value) {
		if (value != null) {
			return value.getId();
		}

		return null;
	}

	public BeneficiarioType convertToEntityAttribute(Integer value) {
		if (value != null) {
			return BeneficiarioType.parse(value);
		}

		return null;
	}

}
