package kr.ac.bokgpt.security.exception;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException() {
        super();
    }
    public EmailNotFoundException(String message) {
        super(message);
    }
}
