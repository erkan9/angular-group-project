package org.angular.AngularProject.handlers;

import org.angular.AngularProject.exceptions.CannotApplyException;
import org.angular.AngularProject.exceptions.CannotLikeException;
import org.angular.AngularProject.exceptions.InvalidCredentialsException;
import org.angular.AngularProject.exceptions.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentials(InvalidCredentialsException exception) {
        logger.error("Caught exception: ", exception);
        return new ResponseEntity<>("Invalid credentials", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(NotFoundException exception) {
        logger.error("Caught exception: ", exception);
        return new ResponseEntity<>("Username not found. Please try again", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CannotApplyException.class)
    public ResponseEntity<String> handleCannotApplyException(CannotApplyException exception) {
        logger.error("Caught exception: ", exception);
        return new ResponseEntity<>("User cannot apply!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CannotLikeException.class)
    public ResponseEntity<String> handleCannotLikeException(CannotLikeException exception) {
        logger.error("Caught exception: ", exception);
        return new ResponseEntity<>("User cannot Like!", HttpStatus.BAD_REQUEST);
    }
}