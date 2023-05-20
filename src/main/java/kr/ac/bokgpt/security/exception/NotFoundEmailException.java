package kr.ac.bokgpt.security.exception;

public class NotFoundEmailException extends RuntimeException {
    public NotFoundEmailException() {
        super();
    }
    public NotFoundEmailException(String message, Throwable cause) {
        super(message, cause);
    }
    public NotFoundEmailException(String message) {
        super(message);
    }
    public NotFoundEmailException(Throwable cause) {
        super(cause);
    }
}
