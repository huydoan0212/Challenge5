package com.example.challenge5.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter

public enum Error {
    UNCATEGORIZED_EXCEPTION("Uncategorized exception", "Uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY("Invalid message key", "Invalid message key", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND("Product not found", "Product not found", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED("Unauthenticated", "Your token cannot be authenticated ", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED("You do not have permission", "You do not have permission", HttpStatus.FORBIDDEN),
    USER_NOT_FOUND("User not found", "User not found", HttpStatus.NOT_FOUND),
    ROLE_NOT_FOUND("Role not found", "Role not found", HttpStatus.NOT_FOUND),
    USER_NOT_EXISTED("User not existed", "User not existed", HttpStatus.NOT_FOUND),
    CAN_NOT_CHANGE_JAVA_TO_JSON("Can not change Java to JSON", "Can not change Java to JSON", HttpStatus.BAD_REQUEST),
    ;
    private String message;
    private String violation;
    private HttpStatusCode statusCode;

    Error(String message, String violation, HttpStatusCode statusCode) {
        this.message = message;
        this.violation = violation;
        this.statusCode = statusCode;
    }
}
