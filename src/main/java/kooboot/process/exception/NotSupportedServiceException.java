package kooboot.process.exception;

public class NotSupportedServiceException extends RuntimeException {
	public NotSupportedServiceException(){
		super();
	}
	
	public NotSupportedServiceException(String message){
		super(message);
	}
}
