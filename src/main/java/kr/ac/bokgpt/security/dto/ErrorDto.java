package kr.ac.bokgpt.security.dto;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public record ErrorDto(
        int status,
        String message,
        List<FieldError> fieldErrors
) {
    public ErrorDto(int status, String message) {
        this(status, message, new ArrayList<>());
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void addFieldError(String objectName, String path, String message) {
        FieldError error = new FieldError(objectName, path, message);
        fieldErrors.add(error);
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }
}
