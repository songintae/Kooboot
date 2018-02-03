package kooboot.sqlservice.exception;

public class SqlNotFoundException extends RuntimeException{
	public SqlNotFoundException(){
		super();
	}
	
	public SqlNotFoundException(String message){
		super(message);
	}
}
