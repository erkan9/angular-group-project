package org.angular.AngularProject.exceptions;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String errorMessage) {
        super(errorMessage);
    }
}