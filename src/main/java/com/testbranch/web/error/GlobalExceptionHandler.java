package com.testbranch.web.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    public static final String ERROR_MSG_PATTERNS = " Error {}";


    @ExceptionHandler({ DataRetrievalFailureException.class, EntityNotFoundException.class, ResourceNotFoundException.class, UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<APIError> handleNotFound(Exception exception) {
        LOGGER.error(ERROR_MSG_PATTERNS, exception.getMessage(), exception);
        return apiExceptionResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler({ ConstraintViolationException.class, DataIntegrityViolationException.class, UserAlreadyExistException.class })
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ResponseEntity<APIError> handleConstraintViolationException(Exception exception) {
        LOGGER.error(ERROR_MSG_PATTERNS, exception.getMessage(), exception);
        return apiExceptionResponse(HttpStatus.PRECONDITION_FAILED, exception.getMessage());
    }


    private ResponseEntity<APIError> apiExceptionResponse(HttpStatus httpStatus, String message) {
        return ResponseEntity.status(httpStatus)
                .body(APIError.builder()
                        .message(message)
                        .timestamp(LocalDateTime.now())
                        .status(httpStatus.toString())
                        .build());
    }
}
