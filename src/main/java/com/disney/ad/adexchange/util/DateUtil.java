package com.disney.ad.adexchange.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DateUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

	private static final String DATE_FORMAT_1 = "yyyy-MM-dd'T'HH:mm:ss";

	private static final String DATE_FORMAT_2 = "yyyy-MM-dd HH:mm:ss";

	private static final String DATE_FORMAT_3 = "yyyy-MM-dd";

	public Timestamp determineCurrentTimestamp() {
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp;
	}

	public Timestamp convertDateAsStringToTimestamp(String dateString) {
		Timestamp timestamp = null;
		DateFormat simpleDateFormat1 = composeDateFormat(DATE_FORMAT_1);
		Date date = parseDate(simpleDateFormat1, dateString);
		if (date == null) {
			DateFormat simpleDateFormat2 = composeDateFormat(DATE_FORMAT_2);
			date = parseDate(simpleDateFormat2, dateString);
		}
		if (date == null) {
			DateFormat simpleDateFormat3 = composeDateFormat(DATE_FORMAT_3);
			date = parseDate(simpleDateFormat3, dateString);
		}
		if (date == null) {
			try {
				Long dateAsLong = Long.parseLong(dateString);
				timestamp = new Timestamp(dateAsLong);
				return timestamp;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		if (date == null)
		{
			LOGGER.error("Date format is invalid" + dateString);
		} else

		{
			timestamp = new Timestamp(date.getTime());
		}
		return timestamp;

	}

	private DateFormat composeDateFormat(String dateString) {
		TimeZone utcTimezone = TimeZone.getTimeZone("UTC");
		DateFormat simpleDateFormat1 = new SimpleDateFormat(dateString);
		simpleDateFormat1.setLenient(false);
		simpleDateFormat1.setTimeZone(utcTimezone);
		return simpleDateFormat1;
	}

	private Date parseDate(DateFormat dateFormat, String dateString) {
		try {
			Date date = dateFormat.parse(dateString);
			return date;
		} catch (ParseException e) {
			return null;
		}
	}

}