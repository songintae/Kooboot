package kooboot.translate.exception;

public class TranslateException extends RuntimeException {
	private TranslateException() {
		super();
	}

	private TranslateException(String message) {
		super(message);
	}

	public TranslateException(Exception e) {
		super(e);
	}
}
