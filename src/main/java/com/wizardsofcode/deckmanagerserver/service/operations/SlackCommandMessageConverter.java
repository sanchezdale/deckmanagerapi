/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/16/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.operations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wizardsofcode.deckmanagerserver.model.operations.SlackCommand;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.Map;

public class SlackCommandMessageConverter extends AbstractHttpMessageConverter<SlackCommand> {


    private static final FormHttpMessageConverter messageConverter = new FormHttpMessageConverter();
    private static final ObjectMapper omap = new ObjectMapper();

    @Override
    protected boolean supports(Class<?> clazz) {
        return (SlackCommand.class.equals(clazz));
    }

    @Override
    protected SlackCommand readInternal(Class<? extends SlackCommand> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        Map<String,String> vals = messageConverter.read(null,inputMessage).toSingleValueMap();

        return omap.convertValue(vals,SlackCommand.class);
    }

    @Override
    protected void writeInternal(SlackCommand command, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
