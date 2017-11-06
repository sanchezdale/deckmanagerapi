/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * *                                      * *
 */


package com.wizardsofcode.deckmanagerserver.model.magicdeck.specification;

public enum SearchOperation {

    GREATER_THAN, LESS_THAN, EQUAL, NOT_EQUAL, LIKE;

    public static final String[] operations = {">","<","=","!","*"};

    public static SearchOperation getOperation(char operation){
        switch (operation){
            case '>':
                return GREATER_THAN;
            case '<':
                return LESS_THAN;
            case '=':
                return EQUAL;
            case '!':
                return NOT_EQUAL;
            case '*':
                return LIKE;
            default:
                return null;

        }
    }

}
