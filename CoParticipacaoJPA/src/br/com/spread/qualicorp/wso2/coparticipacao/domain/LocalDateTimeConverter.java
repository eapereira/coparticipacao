package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class LocalDateTimeConverter
		implements
		AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime value) {
		if (value != null) {
			return Timestamp.valueOf(value);
		}

		return null;
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp value) {
		if (value != null) {
			return value.toLocalDateTime();
		}

		return null;
	}

}
