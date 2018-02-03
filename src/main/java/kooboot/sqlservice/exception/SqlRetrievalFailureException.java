package kooboot.sqlservice.exception;

public class SqlRetrievalFailureException extends RuntimeException {
	public SqlRetrievalFailureException(){
		super();
	}
	
	public SqlRetrievalFailureException(String message){
		super(message);
	}
}
