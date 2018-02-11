package kooboot.util;

public class StringUtil {
	public static boolean isEmpty(String value){
		if(value == null){
			return true;
		}else{
			if("".equals(value))
				return true;
			else
				return false;
		}
		
	}
	
	public static String StringFirstUpper(String value){
		if(isEmpty(value))
			return value;
		else{
			String result = value.substring(0, 1).toUpperCase();
			result += value.substring(1);
			return result;
		}
	}
}
