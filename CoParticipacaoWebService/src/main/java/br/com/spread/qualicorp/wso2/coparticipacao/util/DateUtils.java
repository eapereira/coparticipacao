package br.com.spread.qualicorp.wso2.coparticipacao.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public abstract class DateUtils {
	public static Date dateToSqlDate(LocalDate date) {
		if (date != null) {
			return Date.valueOf(date);
		}

		return null;
	}

	public static Timestamp dateTimeToTimestamp(LocalDateTime localDateTime) {
		if (localDateTime != null) {
			return Timestamp.valueOf(localDateTime);
		}

		return null;
	}
}
