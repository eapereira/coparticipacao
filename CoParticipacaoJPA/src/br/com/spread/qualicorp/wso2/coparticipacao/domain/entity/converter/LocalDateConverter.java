package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

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

			/*
			 * Gambiarra para resolver o BUG no MySQL de retornar a data com um
			 * dia a menos, parece ser um problema com TimZone, mas o gravar a
			 * data esta correto, apenas quando carregamos do banco é que a data
			 * vem com um dia a menos:
			 */
			// return value.toLocalDate();
			return value.toLocalDate().plusDays(NumberUtils.INTEGER_ONE);
		}

		return null;
	}

}
