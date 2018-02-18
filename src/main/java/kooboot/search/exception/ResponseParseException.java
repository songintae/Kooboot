package kooboot.search.exception;

public class ResponseParseException extends RuntimeException {
	public ResponseParseException(String message){
		super(message);
		
	}
	
	public ResponseParseException(Throwable e){
		super(e);
	}
	
}
