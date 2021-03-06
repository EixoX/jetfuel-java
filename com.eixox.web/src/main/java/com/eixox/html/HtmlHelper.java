package com.eixox.html;

public final class HtmlHelper {

	// __________________________________________________________________________________________
	public static final String getAttributeValue(String html, int offset, String attributeName) {
		int attPos = html.indexOf(attributeName, offset);
		if (attPos < offset)
			return null;

		int afterEqualOffset = attPos + attributeName.length();
		char afterEqualChar = html.charAt(afterEqualOffset);

		if (afterEqualChar == '=') {

			char afterEqual = html.charAt(afterEqualOffset + 1);

			char endOfValueLookup;

			switch (afterEqual) {
			case '"':
				afterEqualOffset = afterEqualOffset + 2;
				endOfValueLookup = '"';
				break;
			case '\'':
				afterEqualOffset = afterEqualOffset + 2;
				endOfValueLookup = '\'';
				break;
			default:
				afterEqualOffset = afterEqualOffset + 1;
				endOfValueLookup = ' ';
				break;
			}

			int endOfValuePos = html.indexOf(endOfValueLookup, afterEqualOffset);
			if (endOfValuePos > afterEqualOffset)
				return html.substring(afterEqualOffset, endOfValuePos);
			else
				return null;

		} else {
			return attributeName;
		}
	}

	// __________________________________________________________________________________________
	public static final String extractText(String htmlContent) {

		int left = htmlContent.indexOf('<');
		if (left < 0)
			return htmlContent;

		int right = htmlContent.indexOf('>', left + 1);
		if (right < left)
			return htmlContent;

		int length = htmlContent.length();
		int start = 0;

		StringBuilder builder = new StringBuilder(length);

		do {
			for (int i = start; i < left; i++) {
				builder.append(htmlContent.charAt(i));
			}
			start = right + 1;
			left = htmlContent.indexOf('<', right + 1);
			right = htmlContent.indexOf('>', left + 1);
		} while (left > 0 && right > left);

		while (start < length) {
			builder.append(htmlContent.charAt(start));
			start++;
		}

		return builder.toString();

	}

	// __________________________________________________________________________________________
	public static final String decode(String input) {
		if (input == null || input.isEmpty())
			return input;

		return input.replaceAll("&#8482;", "™").replaceAll("&#32;", "Space").replaceAll("&#33;", "!").replaceAll("&#34;", "\"").replaceAll("&#35;", "#")
				.replaceAll("&#36;", "$").replaceAll("&#37;", "%").replaceAll("&#38;", "&").replaceAll("&#39;", "'").replaceAll("&#40;", "(")
				.replaceAll("&#41;", ")").replaceAll("&#42;", "*").replaceAll("&#43;", "+").replaceAll("&#44;", ",").replaceAll("&#45;", "-")
				.replaceAll("&#46;", ".").replaceAll("&#47;", "/").replaceAll("&#48;", "0").replaceAll("&#49;", "1").replaceAll("&#50;", "2")
				.replaceAll("&#51;", "3").replaceAll("&#52;", "4").replaceAll("&#53;", "5").replaceAll("&#54;", "6").replaceAll("&#55;", "7")
				.replaceAll("&#56;", "8").replaceAll("&#57;", "9").replaceAll("&#58;", ":").replaceAll("&#59;", ";").replaceAll("&#60;", "<")
				.replaceAll("&#61;", "=").replaceAll("&#62;", ">").replaceAll("&#63;", "?").replaceAll("&#64;", "@").replaceAll("&#65;", "A")
				.replaceAll("&#66;", "B").replaceAll("&#67;", "C").replaceAll("&#68;", "D").replaceAll("&#69;", "E").replaceAll("&#70;", "F")
				.replaceAll("&#71;", "G").replaceAll("&#72;", "H").replaceAll("&#73;", "I").replaceAll("&#74;", "J").replaceAll("&#75;", "K")
				.replaceAll("&#76;", "L").replaceAll("&#77;", "M").replaceAll("&#78;", "N").replaceAll("&#79;", "O").replaceAll("&#80;", "P")
				.replaceAll("&#81;", "Q").replaceAll("&#82;", "R").replaceAll("&#83;", "S").replaceAll("&#84;", "T").replaceAll("&#85;", "U")
				.replaceAll("&#86;", "V").replaceAll("&#87;", "W").replaceAll("&#88;", "X").replaceAll("&#89;", "Y").replaceAll("&#90;", "Z")
				.replaceAll("&#91;", "[").replaceAll("&#92;", "\\").replaceAll("&#93;", "]").replaceAll("&#94;", "^").replaceAll("&#95;", "_")
				.replaceAll("&#96;", "`").replaceAll("&#97;", "a").replaceAll("&#98;", "b").replaceAll("&#99;", "c").replaceAll("&#100;", "d")
				.replaceAll("&#101;", "e").replaceAll("&#102;", "f").replaceAll("&#103;", "g").replaceAll("&#104;", "h").replaceAll("&#105;", "i")
				.replaceAll("&#106;", "j").replaceAll("&#107;", "k").replaceAll("&#108;", "l").replaceAll("&#109;", "m").replaceAll("&#110;", "n")
				.replaceAll("&#111;", "o").replaceAll("&#112;", "p").replaceAll("&#113;", "q").replaceAll("&#114;", "r").replaceAll("&#115;", "s")
				.replaceAll("&#116;", "t").replaceAll("&#117;", "u").replaceAll("&#118;", "v").replaceAll("&#119;", "w").replaceAll("&#120;", "x")
				.replaceAll("&#121;", "y").replaceAll("&#122;", "z").replaceAll("&#123;", "{").replaceAll("&#124;", "|").replaceAll("&#125;", "}")
				.replaceAll("&#126;", "~").replaceAll("&#160;", " ").replaceAll("&#161;", "¡").replaceAll("&#162;", "¢").replaceAll("&#163;", "£")
				.replaceAll("&#164;", "¤").replaceAll("&#165;", "¥").replaceAll("&#166;", "¦").replaceAll("&#167;", "§").replaceAll("&#168;", "¨")
				.replaceAll("&#169;", "©").replaceAll("&#170;", "ª").replaceAll("&#171;", "«").replaceAll("&#172;", "¬").replaceAll("&#173;", "­")
				.replaceAll("&#174;", "®").replaceAll("&#175;", "¯").replaceAll("&#176;", "°").replaceAll("&#177;", "±").replaceAll("&#178;", "²")
				.replaceAll("&#179;", "³").replaceAll("&#180;", "´").replaceAll("&#181;", "µ").replaceAll("&#182;", "¶").replaceAll("&#183;", "·")
				.replaceAll("&#184;", "¸").replaceAll("&#185;", "¹").replaceAll("&#186;", "º").replaceAll("&#187;", "»").replaceAll("&#188;", "¼")
				.replaceAll("&#189;", "½").replaceAll("&#190;", "¾").replaceAll("&#191;", "¿").replaceAll("&#192;", "À").replaceAll("&#193;", "Á")
				.replaceAll("&#194;", "Â").replaceAll("&#195;", "Ã").replaceAll("&#196;", "Ä").replaceAll("&#197;", "Å").replaceAll("&#198;", "Æ")
				.replaceAll("&#199;", "Ç").replaceAll("&#200;", "È").replaceAll("&#201;", "É").replaceAll("&#202;", "Ê").replaceAll("&#203;", "Ë")
				.replaceAll("&#204;", "Ì").replaceAll("&#205;", "Í").replaceAll("&#206;", "Î").replaceAll("&#207;", "Ï").replaceAll("&#208;", "Ð")
				.replaceAll("&#209;", "Ñ").replaceAll("&#210;", "Ò").replaceAll("&#211;", "Ó").replaceAll("&#212;", "Ô").replaceAll("&#213;", "Õ")
				.replaceAll("&#214;", "Ö").replaceAll("&#215;", "×").replaceAll("&#216;", "Ø").replaceAll("&#217;", "Ù").replaceAll("&#218;", "Ú")
				.replaceAll("&#219;", "Û").replaceAll("&#220;", "Ü").replaceAll("&#221;", "Ý").replaceAll("&#222;", "Þ").replaceAll("&#223;", "ß")
				.replaceAll("&#224;", "à").replaceAll("&#225;", "á").replaceAll("&#226;", "â").replaceAll("&#227;", "ã").replaceAll("&#228;", "ä")
				.replaceAll("&#229;", "å").replaceAll("&#230;", "æ").replaceAll("&#231;", "ç").replaceAll("&#232;", "è").replaceAll("&#233;", "é")
				.replaceAll("&#234;", "ê").replaceAll("&#235;", "ë").replaceAll("&#236;", "ì").replaceAll("&#237;", "í").replaceAll("&#238;", "î")
				.replaceAll("&#239;", "ï").replaceAll("&#240;", "ð").replaceAll("&#241;", "ñ").replaceAll("&#242;", "ò").replaceAll("&#243;", "ó")
				.replaceAll("&#244;", "ô").replaceAll("&#245;", "õ").replaceAll("&#246;", "ö").replaceAll("&#247;", "÷").replaceAll("&#248;", "ø")
				.replaceAll("&#249;", "ù").replaceAll("&#250;", "ú").replaceAll("&#251;", "û").replaceAll("&#252;", "ü").replaceAll("&#253;", "ý")
				.replaceAll("&#254;", "þ").replaceAll("&#255;", "ÿ").replaceAll("&#256;", "Ā").replaceAll("&#257;", "ā").replaceAll("&#258;", "Ă")
				.replaceAll("&#259;", "ă").replaceAll("&#260;", "Ą").replaceAll("&#261;", "ą").replaceAll("&#262;", "Ć").replaceAll("&#263;", "ć")
				.replaceAll("&#264;", "Ĉ").replaceAll("&#265;", "ĉ").replaceAll("&#266;", "Ċ").replaceAll("&#267;", "ċ").replaceAll("&#268;", "Č")
				.replaceAll("&#269;", "č").replaceAll("&#270;", "Ď").replaceAll("&#271;", "ď").replaceAll("&#272;", "Đ").replaceAll("&#273;", "đ")
				.replaceAll("&#274;", "Ē").replaceAll("&#275;", "ē").replaceAll("&#276;", "Ĕ").replaceAll("&#277;", "ĕ").replaceAll("&#278;", "Ė")
				.replaceAll("&#279;", "ė").replaceAll("&#280;", "Ę").replaceAll("&#281;", "ę").replaceAll("&#282;", "Ě").replaceAll("&#283;", "ě")
				.replaceAll("&#284;", "Ĝ").replaceAll("&#285;", "ĝ").replaceAll("&#286;", "Ğ").replaceAll("&#287;", "ğ").replaceAll("&#288;", "Ġ")
				.replaceAll("&#289;", "ġ").replaceAll("&#290;", "Ģ").replaceAll("&#291;", "ģ").replaceAll("&#292;", "Ĥ").replaceAll("&#293;", "ĥ")
				.replaceAll("&#294;", "Ħ").replaceAll("&#295;", "ħ").replaceAll("&#296;", "Ĩ").replaceAll("&#297;", "ĩ").replaceAll("&#298;", "Ī")
				.replaceAll("&#299;", "ī").replaceAll("&#300;", "Ĭ").replaceAll("&#301;", "ĭ").replaceAll("&#302;", "Į").replaceAll("&#303;", "į")
				.replaceAll("&#304;", "İ").replaceAll("&#305;", "ı").replaceAll("&#306;", "Ĳ").replaceAll("&#307;", "ĳ").replaceAll("&#308;", "Ĵ")
				.replaceAll("&#309;", "ĵ").replaceAll("&#310;", "Ķ").replaceAll("&#311;", "ķ").replaceAll("&#312;", "ĸ").replaceAll("&#313;", "Ĺ")
				.replaceAll("&#314;", "ĺ").replaceAll("&#315;", "Ļ").replaceAll("&#316;", "ļ").replaceAll("&#317;", "Ľ").replaceAll("&#318;", "ľ")
				.replaceAll("&#319;", "Ŀ").replaceAll("&#320;", "ŀ").replaceAll("&#321;", "Ł").replaceAll("&#322;", "ł").replaceAll("&#323;", "Ń")
				.replaceAll("&#324;", "ń").replaceAll("&#325;", "Ņ").replaceAll("&#326;", "ņ").replaceAll("&#327;", "Ň").replaceAll("&#328;", "ň")
				.replaceAll("&#329;", "ŉ").replaceAll("&#330;", "Ŋ").replaceAll("&#331;", "ŋ").replaceAll("&#332;", "Ō").replaceAll("&#333;", "ō")
				.replaceAll("&#334;", "Ŏ").replaceAll("&#335;", "ŏ").replaceAll("&#336;", "Ő").replaceAll("&#337;", "ő").replaceAll("&#338;", "Œ")
				.replaceAll("&#339;", "œ").replaceAll("&#340;", "Ŕ").replaceAll("&#341;", "ŕ").replaceAll("&#342;", "Ŗ").replaceAll("&#343;", "ŗ")
				.replaceAll("&#344;", "Ř").replaceAll("&#345;", "ř").replaceAll("&#346;", "Ś").replaceAll("&#347;", "ś").replaceAll("&#348;", "Ŝ")
				.replaceAll("&#349;", "ŝ").replaceAll("&#350;", "Ş").replaceAll("&#351;", "ş").replaceAll("&#352;", "Š").replaceAll("&#353;", "š")
				.replaceAll("&#354;", "Ţ").replaceAll("&#355;", "ţ").replaceAll("&#356;", "Ť").replaceAll("&#357;", "ť").replaceAll("&#358;", "Ŧ")
				.replaceAll("&#359;", "ŧ").replaceAll("&#360;", "Ũ").replaceAll("&#361;", "ũ").replaceAll("&#362;", "Ū").replaceAll("&#363;", "ū")
				.replaceAll("&#364;", "Ŭ").replaceAll("&#365;", "ŭ").replaceAll("&#366;", "Ů").replaceAll("&#367;", "ů").replaceAll("&#368;", "Ű")
				.replaceAll("&#369;", "ű").replaceAll("&#370;", "Ų").replaceAll("&#371;", "ų").replaceAll("&#372;", "Ŵ").replaceAll("&#373;", "ŵ")
				.replaceAll("&#374;", "Ŷ").replaceAll("&#375;", "ŷ").replaceAll("&#376;", "Ÿ").replaceAll("&#377;", "Ź").replaceAll("&#378;", "ź")
				.replaceAll("&#379;", "Ż").replaceAll("&#380;", "ż").replaceAll("&#381;", "Ž").replaceAll("&#382;", "ž").replaceAll("&#383;", "ſ")
				.replaceAll("&#340;", "Ŕ").replaceAll("&#341;", "ŕ").replaceAll("&#342;", "Ŗ").replaceAll("&#343;", "ŗ").replaceAll("&#344;", "Ř")
				.replaceAll("&#345;", "ř").replaceAll("&#346;", "Ś").replaceAll("&#347;", "ś").replaceAll("&#348;", "Ŝ").replaceAll("&#349;", "ŝ")
				.replaceAll("&#350;", "Ş").replaceAll("&#351;", "ş").replaceAll("&#352;", "Š").replaceAll("&#353;", "š").replaceAll("&#354;", "Ţ")
				.replaceAll("&#355;", "ţ").replaceAll("&#356;", "Ť").replaceAll("&#577;", "ť").replaceAll("&#358;", "Ŧ").replaceAll("&#359;", "ŧ")
				.replaceAll("&#360;", "Ũ").replaceAll("&#361;", "ũ").replaceAll("&#362;", "Ū").replaceAll("&#363;", "ū").replaceAll("&#364;", "Ŭ")
				.replaceAll("&#365;", "ŭ").replaceAll("&#366;", "Ů").replaceAll("&#367;", "ů").replaceAll("&#368;", "Ű").replaceAll("&#369;", "ű")
				.replaceAll("&#370;", "Ų").replaceAll("&#371;", "ų").replaceAll("&#372;", "Ŵ").replaceAll("&#373;", "ŵ").replaceAll("&#374;", "Ŷ")
				.replaceAll("&#375;", "ŷ").replaceAll("&#376;", "Ÿ").replaceAll("&#377;", "Ź").replaceAll("&#378;", "ź").replaceAll("&#379;", "Ż")
				.replaceAll("&#380;", "ż").replaceAll("&#381;", "Ž").replaceAll("&#382;", "ž").replaceAll("&#383;", "ſ").replaceAll("&euro;", "€")
				.replaceAll("&quot;", "\"").replaceAll("&amp;", "&").replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&nbsp;", " ")
				.replaceAll("&iexcl;", "¡").replaceAll("&cent;", "¢").replaceAll("&pound;", "£").replaceAll("&curren;", "¤").replaceAll("&yen;", "¥")
				.replaceAll("&brvbar;", "¦").replaceAll("&sect;", "§").replaceAll("&uml;", "¨").replaceAll("&copy;", "©").replaceAll("&ordf;", "ª")
				.replaceAll("&not;", "¬").replaceAll("&shy;", "­").replaceAll("&reg;", "®").replaceAll("&macr;", "¯").replaceAll("&deg;", "°")
				.replaceAll("&plusmn;", "±").replaceAll("&sup2;", "²").replaceAll("&sup3;", "³").replaceAll("&acute;", "´").replaceAll("&micro;", "µ")
				.replaceAll("&para;", "¶").replaceAll("&middot;", "·").replaceAll("&cedil;", "¸").replaceAll("&sup1;", "¹").replaceAll("&ordm;", "º")
				.replaceAll("&raquo;", "»").replaceAll("&frac14;", "¼").replaceAll("&frac12;", "½").replaceAll("&frac34;", "¾").replaceAll("&iquest;", "¿")
				.replaceAll("&Agrave;", "À").replaceAll("&Aacute;", "Á").replaceAll("&Acirc;", "Â").replaceAll("&Atilde;", "Ã").replaceAll("&Auml;", "Ä")
				.replaceAll("&Aring;", "Å").replaceAll("&AElig;", "Æ").replaceAll("&Ccedil;", "Ç").replaceAll("&Egrave;", "È").replaceAll("&Eacute;", "É")
				.replaceAll("&Ecirc;", "Ê").replaceAll("&Euml;", "Ë").replaceAll("&Igrave;", "Ì").replaceAll("&Iacute;", "Í").replaceAll("&Icirc;", "Î")
				.replaceAll("&Iuml;", "Ï").replaceAll("&ETH;", "Ð").replaceAll("&Ntilde;", "Ñ").replaceAll("&Ograve;", "Ò").replaceAll("&Oacute;", "Ó")
				.replaceAll("&Ocirc;", "Ô").replaceAll("&Otilde;", "Õ").replaceAll("&Ouml;", "Ö").replaceAll("&times;", "×").replaceAll("&Oslash;", "Ø")
				.replaceAll("&Ugrave;", "Ù").replaceAll("&Uacute;", "Ú").replaceAll("&Ucirc;", "Û").replaceAll("&Uuml;", "Ü").replaceAll("&Yacute;", "Ý")
				.replaceAll("&THORN;", "Þ").replaceAll("&szlig;", "ß").replaceAll("&agrave;", "à").replaceAll("&aacute;", "á").replaceAll("&acirc;", "â")
				.replaceAll("&atilde;", "ã").replaceAll("&auml;", "ä").replaceAll("&aring;", "å").replaceAll("&aelig;", "æ").replaceAll("&ccedil;", "ç")
				.replaceAll("&egrave;", "è").replaceAll("&eacute;", "é").replaceAll("&ecirc;", "ê").replaceAll("&euml;", "ë").replaceAll("&igrave;", "ì")
				.replaceAll("&iacute;", "í").replaceAll("&icirc;", "î").replaceAll("&iuml;", "ï").replaceAll("&eth;", "ð").replaceAll("&ntilde;", "ñ")
				.replaceAll("&ograve;", "ò").replaceAll("&oacute;", "ó").replaceAll("&ocirc;", "ô").replaceAll("&otilde;", "õ").replaceAll("&ouml;", "ö")
				.replaceAll("&divide;", "÷").replaceAll("&oslash;", "ø").replaceAll("&ugrave;", "ù").replaceAll("&uacute;", "ú").replaceAll("&ucirc;", "û")
				.replaceAll("&uuml;", "ü").replaceAll("&yacute;", "ý").replaceAll("&thorn;", "þ");

	}
}
