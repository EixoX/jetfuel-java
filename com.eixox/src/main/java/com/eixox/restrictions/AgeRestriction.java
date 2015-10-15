package com.eixox.restrictions;

import java.util.Calendar;
import java.util.Date;

import com.eixox.Dates;

public class AgeRestriction implements Restriction {

	private final int _Min;
	private final int _Max;

	public AgeRestriction(int min, int max) {
		this._Min = min;
		this._Max = max;
	}

	public AgeRestriction(Age age) {
		this(age.min(), age.max());
	}

	public final boolean validate(Date date) {
		int years = Dates.yearsBetween(new Date(), date);
		return years >= _Min && years <= _Max;
	}

	public final boolean validate(Calendar calendar) {
		int years = Dates.yearsBetween(Calendar.getInstance(), calendar);
		return years >= _Min && years <= _Max;
	}

	public final boolean validate(Object input) {
		if (input == null)
			return false;
		else if (input instanceof Date)
			return validate((Date) input);
		else if (input instanceof Calendar)
			return validate((Calendar) input);
		else
			return false;
	}

	public String getRestrictionMessageFor(Object input) {
		return validate(input) ? null : "Você tem que ter entre " + Integer.toString(this._Min) + " e " + Integer.toString(this._Max) + " anos.";
	}

	public void assertValid(Object input) throws RestrictionException {
		String message = getRestrictionMessageFor(input);
		if (message != null)
			throw new RestrictionException(message);

	}
}
