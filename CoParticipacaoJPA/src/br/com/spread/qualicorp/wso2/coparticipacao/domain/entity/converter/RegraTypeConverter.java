package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter;

import javax.persistence.AttributeConverter;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class RegraTypeConverter
		implements
		AttributeConverter<RegraType, Integer> {

	public Integer convertToDatabaseColumn(RegraType value) {
		if (value != null) {
			return value.getId();
		}

		return null;
	}

	public RegraType convertToEntityAttribute(Integer value) {
		if (value != null) {
			return RegraType.parse(value);
		}

		return null;
	}

}
