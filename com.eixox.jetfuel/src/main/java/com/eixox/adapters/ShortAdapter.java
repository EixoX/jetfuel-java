package com.eixox.adapters;

public class ShortAdapter implements ValueAdapter<Short> {

	
	public final Short adapt(Object input) {
		if (input == null)
			return null;
		else if (input instanceof Short)
			return ((Short) input);
		else if (input instanceof Number)
			return ((Number) input).shortValue();
		else
			return Short.parseShort(input.toString());
	}

	
	public final Short parse(String input) {
		return Short.parseShort(input);
	}

	
	public final String format(Short input) {
		return input.toString();
	}

}
