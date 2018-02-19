package kooboot.user.exception;

public class NotExistStatusException extends RuntimeException {

	public NotExistStatusException() {

	}

	public NotExistStatusException(String message) {
		super(message);
	}
}
