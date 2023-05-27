package kr.ac.bokgpt.security.exception;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException() {
        super();
    }
    public CommentNotFoundException(String message) {
        super(message);
    }
}
