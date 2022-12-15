package com.arash.hexagonal.infrastructure.input.exception;

import com.arash.hexagonal.domain.exception.DomainConflictException;
import com.arash.hexagonal.domain.exception.DomainNotFoundException;
import com.arash.hexagonal.domain.exception.RemoveDomainException;
import com.arash.hexagonal.infrastructure.input.response.ResponseFactory;
import com.arash.hexagonal.infrastructure.input.response.RestResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * @author a.ariani
 */
@ControllerAdvice
public class RestAPIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DomainNotFoundException.class})
    protected ResponseEntity<?> handleEntityNotFoundException(
            DomainNotFoundException ex) {
        return ResponseFactory.notFound(ex.getMessage());
    }

    @ExceptionHandler(value = {DomainConflictException.class})
    protected ResponseEntity<?> handleConflictException(
            DomainConflictException ex) {
        return ResponseFactory.conflict(ex.getMessage());
    }

    @ExceptionHandler(value = {JdbcSQLIntegrityConstraintViolationException.class})
    protected ResponseEntity<?> handleJdbcSQLIntegrityConstraintViolationException(
            JdbcSQLIntegrityConstraintViolationException ex) {
        return ResponseFactory.badRequest("unique constraint exception");
    }

    @ExceptionHandler(value = {RemoveDomainException.class})
    protected ResponseEntity<?> handleConflictRemoveDomain(
            RemoveDomainException ex) {
        return ResponseFactory.notAccepted(ex.getMessage());
    }


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        RestResponse<?> response = new RestResponse<>();
        response.setMessage(ex.getMessage());
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setHasError(true);
        return ResponseEntity.badRequest().body(response);
    }


    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        RestResponse<?> response = new RestResponse<>();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(ex.getMessage());
        response.setHasError(true);
        return ResponseEntity.badRequest().body(response);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        RestResponse<?> response = new RestResponse<>();
        response.setMessage(ex.getMessage());
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setHasError(true);
        return ResponseEntity.badRequest().body(response);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        RestResponse<?> restResponse = new RestResponse<>();
        restResponse.setHasError(true);
        if (ex.getCause() instanceof JsonParseException || ex.getCause() instanceof JsonMappingException) {
            restResponse.setMessage("Invalid json format");
        }
        restResponse.setCode(status.value());
        return ResponseEntity.badRequest().body(restResponse);
    }

}
