/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/8/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.magicdeck.specification;

public class SearchCriteria {

    public String fieldName;
    public SearchOperation operation;
    public Object value;

    public SearchCriteria(String fieldName, SearchOperation operation, Object value) {
        this.fieldName = fieldName;
        this.operation = operation;
        this.value = value;
    }
}
