package com.eixox.adapters;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.eixox.globalization.Culture;

public final class ShortAdapter extends ValueAdapter<Short> {

	public ShortAdapter() {
		super(Short.class);
	}

	@Override
	public final Short parse(Culture culture, String input) {
		Number n = culture.parseNumber(input);
		return n == null ? 0 : n.shortValue();
	}

	@Override
	public final String format(Culture culture, Short input) {
		return culture.formatNumber(input);
	}

	@Override
	public final boolean IsNullOrEmpty(Object item) {
		return item == null || ((Short) item) == 0;
	}

	@Override
	public final Short convert(Object value, Culture culture) {
		if (value == null)
			return 0;
		else if (Short.class.isInstance(value) || Short.TYPE.isInstance(value))
			return (Short) value;
		else if (Number.class.isInstance(value))
			return ((Number) value).shortValue();
		else if (String.class.isInstance(value))
			return parse(culture, (String) value);
		else
			return 0;
	}

	@Override
	public int getSqlTypeId() {
		return java.sql.Types.SMALLINT;
	}

	@Override
	public void setParameterValue(PreparedStatement ps, int parameterIndex, Short value) throws SQLException {
		ps.setShort(parameterIndex, value);
	}

	@Override
	public Short readValue(ResultSet rs, int ordinal) throws SQLException {
		return rs.getShort(ordinal);
	}
}
