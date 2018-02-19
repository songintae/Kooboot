package kooboot.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

	public static String getDateByFomrmat(Date arg, String format) {
		if (StringUtil.isEmpty(format))
			return "";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(arg);
	}

	public static String getDateYyyymmddhhmmss(Date arg) {
		return getDateByFomrmat(arg, "yyyyMMddHHmmss");
	}

	public static String getDateYyyymmddhhmmssWithDelimiter(Date arg) {
		return getDateByFomrmat(arg, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date getDateWithISO8601Format(String ISO8601Time) {
		return javax.xml.bind.DatatypeConverter.parseDateTime(ISO8601Time).getTime();
	}

	public static String getDateYyyymmddhhmmssWithDelimiterWithISO8601(String ISO8601Time) {
		return getDateYyyymmddhhmmssWithDelimiter(getDateWithISO8601Format(ISO8601Time));
	}
}
