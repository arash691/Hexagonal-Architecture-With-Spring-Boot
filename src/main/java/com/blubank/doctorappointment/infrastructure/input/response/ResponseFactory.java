package com.blubank.doctorappointment.infrastructure.input.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.function.Function;


/**
 * @author a.ariani
 */

public class ResponseFactory<T> {

    public static <T> ResponseEntity<RestResponse<T>> ok(T result, String message) {
        RestResponse<T> response = new RestResponse<>();
        response.setBody(result);
        response.setCode(HttpStatus.OK.value());
        response.setMessage(message);
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<RestResponse<T>> ok(T result) {
        RestResponse<T> response = new RestResponse<>();
        response.setBody(result);
        response.setCode(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    public static <T, R> ResponseEntity<RestResponse<R>> ok(T result, Function<T, R> mapper) {
        RestResponse<R> response = new RestResponse<>();
        response.setBody(mapper.apply(result));
        response.setCode(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }


    public static <T> ResponseEntity<RestResponse<T>> created(T result, String message, String uri) {
        RestResponse<T> response = new RestResponse<>();
        response.setBody(result);
        response.setCode(HttpStatus.CREATED.value());
        response.setMessage(message);
        return ResponseEntity.created(URI.create(uri)).body(response);
    }

    public static <T> ResponseEntity<RestResponse<T>> noContent(String message) {
        RestResponse<T> response = new RestResponse<>();
        response.setCode(HttpStatus.NO_CONTENT.value());
        response.setMessage(message);
        return ResponseEntity.noContent().build();
    }

    public static <T> ResponseEntity<RestResponse<T>> notFound(String message) {
        RestResponse<T> response = new RestResponse<>();
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(message);
        response.setHasError(true);
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(response);
    }

    public static <T> ResponseEntity<RestResponse<T>> accepted(T result, String message) {
        RestResponse<T> response = new RestResponse<>();
        response.setBody(result);
        response.setCode(HttpStatus.ACCEPTED.value());
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    public static <T> ResponseEntity<RestResponse<T>> badRequest(String message) {
        RestResponse<T> response = new RestResponse<>();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(message);
        response.setHasError(true);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    public static <T> ResponseEntity<RestResponse<T>> notAccepted(String message) {
        RestResponse<T> response = new RestResponse<>();
        response.setCode(HttpStatus.NOT_ACCEPTABLE.value());
        response.setMessage(message);
        response.setHasError(true);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
    }
}
