package com.eixox.restrictions;

public class MinNumberExclusiveRestriction implements Restriction {

	private final double _Value;

	public MinNumberExclusiveRestriction(double value) {
		this._Value = value;
	}

	public MinNumberExclusiveRestriction(MinNumberExclusive MinNumberExclusive) {
		this(MinNumberExclusive.value());
	}

	public final double getValue() {
		return this._Value;
	}

	
	public final boolean validate(Object input) {
		if (input == null || !(input instanceof Number))
			return false;
		else
			return ((Number) input).doubleValue() > _Value;
	}

	
	public final String getRestrictionMessageFor(Object input) {
		return validate(input) ? null : "N�mero abaixo do m�nimo (exclusivo) " + _Value;
	}

	
	public final void assertValid(Object input) throws RestrictionException {
		String msg = getRestrictionMessageFor(input);
		if (msg != null)
			throw new RestrictionException(msg);

	}

}
