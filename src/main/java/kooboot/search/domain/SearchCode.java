package kooboot.search.domain;


public enum SearchCode {
	INIT("I"), REQ("R"),
	WEB("1"), BOOK("2"), KEYWORD("3");
	
	
	public static final String SEARCH_CATAGORY_WEB = "웹";
	public static final String SEARCH_CATAGORY_BOOK= "도서";
	public static final String SEARCH_CATAGORY_KEYWORD = "키워드 장소";
	
	private final String value;
	SearchCode(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
	
	public static SearchCode valueOfCode(String value){
		switch(value){
			case "I" : return INIT;
			case "R" : return REQ;
			case "1" : return WEB;
			case SEARCH_CATAGORY_WEB : return WEB;
			case "2" : return BOOK;
			case SEARCH_CATAGORY_BOOK : return BOOK;
			case "3" : return KEYWORD;
			case SEARCH_CATAGORY_KEYWORD : return KEYWORD;
			default :
				throw new AssertionError("Unknown value: " + value);
		}
	}
}
