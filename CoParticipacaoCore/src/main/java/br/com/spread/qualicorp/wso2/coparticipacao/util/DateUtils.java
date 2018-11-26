package br.com.spread.qualicorp.wso2.coparticipacao.util;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;

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

	public static Date localDateToSqlDate(LocalDate localDate) {
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

	public static Time localTimeToTime(LocalTime localTime) {
		if (localTime != null) {
			return Time.valueOf(localTime);
		}

		return null;
	}

	public static String dateToString(LocalDate date, String format) throws CoParticipacaoException {
		DateTimeFormatter dateTimeFormatter;

		if (date != null) {
			dateTimeFormatter = DateTimeFormatter.ofPattern(format);

			return dateTimeFormatter.format(date);
		}

		return null;
	}

	public static boolean isNotZeroDate(String date) {
		if ("00/00/00".equals(date) || "00/00/0000".equals(date)) {
			return false;
		}

		return true;
	}
}
