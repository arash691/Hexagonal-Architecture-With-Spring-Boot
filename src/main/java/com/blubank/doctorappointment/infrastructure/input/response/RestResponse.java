package com.blubank.doctorappointment.infrastructure.input.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author a.ariani
 */
public class RestResponse<T> implements Serializable {
    private String traceId;
    private String traceTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T body;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    private int code;
    private boolean hasError;

    public RestResponse() {
        this.traceId = UUID.randomUUID().toString();
        this.traceTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getTraceTime() {
        return traceTime;
    }

    public void setTraceTime(String traceTime) {
        this.traceTime = traceTime;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }
}
