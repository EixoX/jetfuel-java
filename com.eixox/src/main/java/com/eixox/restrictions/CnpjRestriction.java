package com.eixox.restrictions;

public class CnpjRestriction implements Restriction {

	public CnpjRestriction() {
	}

	public CnpjRestriction(Cnpj Cnpj) {
		// just complying to a constructor pattern.
	}

	public static boolean isValid(long value) {
		if (
				value < 1000000 ||
				value == 11111111111111L ||
                value == 22222222222222L ||
                value == 33333333333333L ||
                value == 44444444444444L ||
                value == 55555555555555L ||
                value == 66666666666666L ||
                value == 77777777777777L ||
                value == 88888888888888L ||
                value == 99999999999999L)
                return false;


            long a = ((value / 10000000000000L) % 10);
            long b = ((value / 1000000000000L) % 10);
            long c = ((value / 100000000000L) % 10);
            long d = ((value / 10000000000L) % 10);
            long e = ((value / 1000000000L) % 10);
            long f = ((value / 100000000L) % 10);
            long g = ((value / 10000000L) % 10);
            long h = ((value / 1000000L) % 10);
            long i = ((value / 100000L) % 10);
            long j = ((value / 10000L) % 10);
            long k = ((value / 1000L) % 10);
            long l = ((value / 100L) % 10);

            //Note: compute 1st verification digit.
            long d1 = ((a * 6 + b * 7 + c * 8 + d * 9) + (e * 2 + f * 3 + g * 4 + h * 5) + (i * 6 + j * 7 + k * 8 + l * 9)) % 11;
            if (d1 == 10)
                d1 = 0;
            //d1 = (d1 == 10 ? 0 : 11 - d1);

            //Note: compute 2nd verification digit.
            long d2 = ((a * 5 + b * 6 + c * 7 + d * 8) + (e * 9 + f * 2 + g * 3 + h * 4) + (i * 5 + j * 6 + k * 7 + l * 8) + (9 * d1)) % 11;
            if (d2 == 10)
                d2 = 0;
            //d2 = (d2 == 10  ? 0 : 11 - d2);

            return (d1 == ((value / 10) % 10) && d2 == (value % 10));
	}

	public boolean validate(Object input) {
		if (input == null)
			return true;
		else if (input instanceof String) {
			String is = (String) input;
			if (is.isEmpty())
				return true;
			else
				return isValid(Long.parseLong(is));
		} else if (input instanceof Number)
			return isValid(((Number) input).longValue());
		else
			return false;
	}

	public String getRestrictionMessageFor(Object input) {
		return validate(input) ? null : "Cnpj invalido";
	}

	public void assertValid(Object input) throws RestrictionException {
		String msg = getRestrictionMessageFor(input);
		if (msg != null)
			throw new RestrictionException(msg);

	}
}
