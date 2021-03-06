package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter;

import javax.persistence.AttributeConverter;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.UserStatusType;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class UserStatusTypeConverter
		implements
		AttributeConverter<UserStatusType, Integer> {

	public Integer convertToDatabaseColumn(UserStatusType value) {
		if (value != null) {
			return value.getId();
		}

		return null;
	}

	public UserStatusType convertToEntityAttribute(Integer value) {
		if (value != null) {
			return UserStatusType.parse(value);
		}

		return null;
	}

}
