package com.eixox.restrictions;

public class NumberRangeRestriction implements Restriction {

	private final double min;
	private final double max;

	public NumberRangeRestriction(double min, double max) {
		this.min = min;
		this.max = max;
	}

	public NumberRangeRestriction(NumberRange numberRange) {
		this(numberRange.min(), numberRange.max());
	}

	public final double getMin() {
		return this.min;
	}

	public final double getMax() {
		return this.max;
	}

	
	public final boolean validate(Object input) {
		if (input == null || !(input instanceof Number))
			return false;
		else {
			double v = ((Number) input).doubleValue();
			return v >= min && v <= max;
		}
	}

	
	public final String getRestrictionMessageFor(Object input) {
		return validate(input) ? null : "Valor fora do range [" + min + ", " + max + "]";
	}

	
	public final void assertValid(Object input) throws RestrictionException {
		String msg = getRestrictionMessageFor(input);
		if (msg != null)
			throw new RestrictionException(msg);

	}

}
