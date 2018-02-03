package kooboot.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil extends Date {
	public DateUtil(){
		super();
	};
	public DateUtil(String dateString){
		super(dateString);
	}
	
	public DateUtil(Date arg){
		super(arg.getTime());
	}

	
	public static String getDateByFomrmat(Date arg, String format){
		if(StringUtil.isEmpty(format))
			return "";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(arg);
	}
	
	public static String getDateYyyymmddhhmmss(Date arg){
		return getDateByFomrmat(arg,"yyyyMMddHHmmss");
	}
}
