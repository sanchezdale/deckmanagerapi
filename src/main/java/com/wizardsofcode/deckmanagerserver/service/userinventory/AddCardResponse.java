/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/25/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.userinventory;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddCardResponse {


    private String result;

    @JsonProperty(value = "notProcessed")
    private List<Integer> notAdded;

    private String message;

    public AddCardResponse() {
    }

    public AddCardResponse(String result, List<Integer> notAdded, String message) {
        this.result = result;
        this.notAdded = notAdded;
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Integer> getNotAdded() {
        return notAdded;
    }

    public void setNotAdded(List<Integer> notAdded) {
        this.notAdded = notAdded;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddCardResponse that = (AddCardResponse) o;

        if (result != null ? !result.equals(that.result) : that.result != null) return false;
        if (notAdded != null ? !notAdded.equals(that.notAdded) : that.notAdded != null) return false;
        return message != null ? message.equals(that.message) : that.message == null;
    }

    @Override
    public int hashCode() {
        int result1 = result != null ? result.hashCode() : 0;
        result1 = 31 * result1 + (notAdded != null ? notAdded.hashCode() : 0);
        result1 = 31 * result1 + (message != null ? message.hashCode() : 0);
        return result1;
    }

    @Override
    public String toString() {
        return "AddCardResponse{" +
                "result='" + result + '\'' +
                ", notAdded=" + notAdded +
                ", message='" + message + '\'' +
                '}';
    }
}
