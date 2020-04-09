package org.csu.mypetstore.exception;

import org.springframework.http.HttpStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ApiException {
    private final ZonedDateTime timestamp;
    private final String message;
    private final HttpStatus status;
    private final boolean error = true;
//    private final Throwable throwable;
//    private final String debugMessage;
//    private final List<ApiSubError> subErrors;

    public ApiException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.timestamp = ZonedDateTime.now(ZoneId.of("Z"));
    }

    public ApiException(String message, HttpStatus status, ZonedDateTime timestamp) {
        this.message = message;
        this.status = status;
//        this.throwable = throwable;
        this.timestamp = timestamp;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public boolean isError() {
        return error;
    }
}
