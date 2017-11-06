package com.wizardsofcode.deckmanagerserver.model.operations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.wizardsofcode.deckmanagerserver.model.magicdeck.View;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@JsonInclude(content = JsonInclude.Include.NON_NULL)
public class RestResponse implements Serializable {

    @JsonView(View.Summary.class)
    private String exception;

    @JsonView(View.Summary.class)
    private String message;

    @JsonView(View.Summary.class)
    private HttpStatus status;

    @JsonView(View.Summary.class)
    private String requestId;

    public RestResponse() {
    }

    public RestResponse(String exception, String message, HttpStatus status) {
        this.exception = exception;
        this.message = message;
        this.status = status;
    }

    public RestResponse(String exception, String message, HttpStatus status, String requestId) {
        this.exception = exception;
        this.message = message;
        this.status = status;
        this.requestId = requestId;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestResponse that = (RestResponse) o;

        if (!exception.equals(that.exception)) return false;
        if (!message.equals(that.message)) return false;
        return status.equals(that.status);
    }

    @Override
    public int hashCode() {
        int result = exception != null ? exception.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (requestId != null ? requestId.hashCode() : 0);
        return result;
    }
}
