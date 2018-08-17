package br.com.spread.qualicorp.wso2.coparticipacao.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

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

	public static LocalDate dateToLocalDate(Date date) {
		if (date != null) {
			return date.toLocalDate();
		}

		return null;
	}

	public static LocalDateTime timestampToLocalDateTime(Timestamp timestamp) {
		if (timestamp != null) {
			return timestamp.toLocalDateTime();
		}

		return null;
	}

	public static LocalDate dateToLocalDate(java.util.Date date) {
		if (date != null) {
			return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}

		return null;
	}

	public static LocalDate now() {
		return LocalDate.now();
	}

	public static Date localDateTimeToSqlDate(LocalDate localDate) {
		if (localDate != null) {
			return Date.valueOf(localDate);
		}

		return null;
	}

	public static LocalDate stringToDate(String value, String format) {
		DateTimeFormatter dateTimeFormatter;

		if (StringUtils.isNotBlank(value)) {
			dateTimeFormatter = DateTimeFormatter.ofPattern(format);

			return LocalDate.parse(value, dateTimeFormatter);
		}

		return null;
	}

	public static LocalDate stringToDate(String value, String format, String localePattern) {
		DateTimeFormatter dateTimeFormatter;
		Locale locale;

		if (StringUtils.isNotBlank(value)) {
			locale = new Locale(localePattern);
			dateTimeFormatter = DateTimeFormatter.ofPattern(format, locale);

			return LocalDate.parse(value, dateTimeFormatter);
		}

		return null;
	}

}
