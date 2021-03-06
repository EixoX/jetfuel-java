package com.eixox.adapters;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.eixox.globalization.Culture;

public final class DateTimeAdapter extends ValueAdapter<Date> {

	public DateTimeAdapter() {
		super(Date.class);
	}

	@Override
	public final Date parse(Culture culture, String input) {
		return culture.parseDate(input);
	}

	@Override
	public final String format(Culture culture, Date input) {
		return input == null ? "" : culture.formatDateTime(input);
	}

	public static final DateFormat SqlDateFormatter = new SimpleDateFormat("''yyyy-MM-dd HH:mm:ss.S''", Locale.US);

	@Override
	public final boolean IsNullOrEmpty(Object item) {
		return item == null || ZERO.equals(item);
	}

	@Override
	public final Date convert(Object value, Culture culture) {
		if (value == null)
			return null;
		else if (Date.class.isInstance(value))
			return (Date) value;
		else if (Number.class.isInstance(value))
		{
			Date dt = new Date();
			dt.setTime(((Number) value).longValue());
			return dt;
		}
		else if (String.class.isInstance(value))
			return parse(culture, (String) value);
		else
			return ZERO;
	}

	public static final Date ZERO;
	static {
		ZERO = new Date();
		ZERO.setTime(0);
	}

	@Override
	public int getSqlTypeId() {
		return java.sql.Types.TIMESTAMP;
	}

	@Override
	public void setParameterValue(PreparedStatement ps, int parameterIndex, Date value) throws SQLException {
		ps.setDate(parameterIndex, new java.sql.Date(value.getTime()));
	}

	@Override
	public Date readValue(ResultSet rs, int ordinal) throws SQLException {
		return rs.getDate(ordinal);
	}

	public static final SimpleDateFormat DATETIME_PTBR = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public static final SimpleDateFormat DATE_PTBR = new SimpleDateFormat("dd/MM/yyyy");

	public static final Date parsePtBrDateTime(String input) {
		try {
			if (input == null || input.isEmpty())
				return null;
			else
				return DATETIME_PTBR.parse(input);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static final Date parsePtBrDate(String input) {
		try {
			if (input == null || input.isEmpty())
				return null;
			else
				return DATE_PTBR.parse(input);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
