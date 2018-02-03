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
}
