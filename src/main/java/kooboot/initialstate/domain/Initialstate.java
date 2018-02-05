package kooboot.initialstate.domain;

import kooboot.translate.domain.TranslateCode;

public enum Initialstate {
	INIT("0"), DELAY("1");
	
	String value;
	
	private Initialstate(String value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
	
	
	public static Initialstate valueOfCode(String value){
		switch(value){
			case "0" : return INIT;
			case "1" : return DELAY;
			default :
				throw new AssertionError("Unknown value: " + value);
		}
	}
}
