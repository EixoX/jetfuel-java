package com.eixox.formatters;

import com.eixox.globalization.Culture;

public interface ValueFormatter<T> {

	public String format(T value, Culture culture);
	
	public String formatObject(Object value, Culture culture);
}
