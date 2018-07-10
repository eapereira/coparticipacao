package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class LocalDateConverter
		implements
		AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate value) {
		if (value != null) {
			return Date.valueOf(value);
		}

		return null;
	}

	@Override
	public LocalDate convertToEntityAttribute(Date value) {
		if (value != null) {
			return value.toLocalDate();
		}

		return null;
	}

}
