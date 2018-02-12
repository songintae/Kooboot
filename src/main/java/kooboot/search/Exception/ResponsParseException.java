package kooboot.search.Exception;

public class ResponsParseException extends RuntimeException {
	public ResponsParseException(String message){
		super(message);
		
	}
	
	public ResponsParseException(Throwable e){
		super(e);
	}
	
}
