package kooboot.user.domain.status;

import org.hsqldb.lib.StringUtil;

public enum StatusCode {
	
	INIT("init"),SEARCH("search"),TRANSLATE("translate");
	final private String status;
	
	StatusCode(String status){
		this.status = status;
	}
	
	public String getValue(){
		return status;
	}
	
	public static StatusCode valueOfCode(String value){
		switch(emptyStringValue(value)){
			case "init" : return INIT;
			case "search" : return SEARCH;
			case "translate" : return TRANSLATE;
			default :
				throw new AssertionError("Unknown value: " + value);
		}
	}
	
	private static String emptyStringValue(String value){
		if(StringUtil.isEmpty(value))
			return "init";
		else
			return value;
	}
}
