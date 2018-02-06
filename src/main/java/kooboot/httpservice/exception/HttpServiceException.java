package kooboot.httpservice.exception;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class HttpServiceException extends RuntimeException{
	public HttpServiceException(){
		
	}
	
	public HttpServiceException(String message){
		super(message);
	}
	
	public HttpServiceException(IOException e){
		super(e);
	}
	
	public HttpServiceException(ClientProtocolException e){
		super(e);
	}

}