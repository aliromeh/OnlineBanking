package com.gbis.Bank.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Converter {

	public static Date toSqlDate(java.util.Date input) {
		return new Date(input.getTime());
	}

	public static java.util.Date toJavaUtilDate(Date input) {
		return new java.util.Date(input.getTime());
	}

	public static Timestamp toSqlDateTime(LocalDateTime input) {
		return Timestamp.valueOf(input);
	}

	public static LocalDateTime toJavaUtilDateTime(Timestamp input) {
		return input.toLocalDateTime();
	}

	public static long stringToLong(String id) {
		long result = 0;
		try {
			result = Long.parseLong(id);
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}

}
