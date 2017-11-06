/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/16/17                                 
 */

package com.wizardsofcode.deckmanagerserver.config;

import com.wizardsofcode.deckmanagerserver.service.operations.SlackCommandMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Configuration
public class MessageConverterConfiguration extends WebMvcConfigurerAdapter{

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        SlackCommandMessageConverter slackSlashCommandConverter = new SlackCommandMessageConverter();
        MediaType mediaType = new MediaType("application","x-www-form-urlencoded", Charset.forName("UTF-8"));
        slackSlashCommandConverter.setSupportedMediaTypes(Arrays.asList(mediaType));
        converters.add(slackSlashCommandConverter);
        super.configureMessageConverters(converters);
    }
}