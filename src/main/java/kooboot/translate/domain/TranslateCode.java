package kooboot.translate.domain;

public enum TranslateCode {
	INIT("","","I"), REQ("","","R"),
	KO_TO_EN("ko","en","1"), KO_TO_JA("ko","ja","2"), KO_TO_CN("ko","zh-CN","3"),
	EN_TO_KO("en","ko","4"), JA_TO_KO("ja","ko","5"),CN_TO_KO("zh-CN","ko","6");
	
	private final String source;
	private final String target;
	private final String value;
	
	TranslateCode(String source , String target, String value){
		this.source = source;
		this.target = target;
		this.value = value;
	}
	
	public String getSource() {
		return source;
	}

	public String getTarget() {
		return target;
	}
	
	public String getValue(){
		return value;
	}
	public static TranslateCode valueOfCode(String value){
		switch(value){
			case "I" : return INIT;
			case "R" : return REQ;
			case "1" : return KO_TO_EN;
			case "2" : return KO_TO_JA;
			case "3" : return KO_TO_CN;
			case "4" : return EN_TO_KO;
			case "5" : return JA_TO_KO;
			case "6" : return CN_TO_KO;
			default :
				throw new AssertionError("Unknown value: " + value);
		}
	}
}
