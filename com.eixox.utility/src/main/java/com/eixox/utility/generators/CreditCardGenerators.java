package com.eixox.utility.generators;

import com.eixox.utility.generators.creditCard.AmericanExpress;
import com.eixox.utility.generators.creditCard.DinersClubInternational;
import com.eixox.utility.generators.creditCard.Maestro;
import com.eixox.utility.generators.creditCard.Mastercard;
import com.eixox.utility.generators.creditCard.Visa;

public class CreditCardGenerators {

	public static final AmericanExpress AMEX = new AmericanExpress();
	public static final Maestro MAESTRO = new Maestro();
	public static final Visa VISA = new Visa();
	public static final Mastercard MASTERCARD = new Mastercard();
	public static final DinersClubInternational DINERS_INTERNATIONAL = new DinersClubInternational();
}
