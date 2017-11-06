/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/3/17                                 
 */

package com.wizardsofcode.deckmanagerserver.model.communication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;

@Entity
@Table(name = "email_template")
public class UserEmailTemplate implements Serializable{


    private static final long serialVersionUID = -8278653183905625712L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int templateId;

    private String name;

    private String subject;

    @Lob
    private String content;

    @Transient
    private ParametersMapper mapper;

    public UserEmailTemplate() {
        mapper = new ParametersMapper();
    }

    public UserEmailTemplate(String subject, String content) {
        this.subject = subject;
        this.content = content;
        mapper = new ParametersMapper();
    }

    public UserEmailTemplate(UserEmailTemplate copy){
        this.content = copy.content;
        this.subject = copy.subject;
        this.mapper = new ParametersMapper();
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonIgnore
    @Transient
    public void runMapper(HashMap<String,String> parameters){
        mapper.setParameter(parameters);
        this.content = mapper.mapParameters(content);
    }


    public class ParametersMapper{

        private HashMap<String,String> parameter;


        public ParametersMapper() {
            this.parameter = new HashMap<>();
        }

        public void addParameter(String parameterName, String parameterValue){
            parameter.put(parameterName, parameterValue);
        }

        public HashMap<String, String> getParameter() {
            return parameter;
        }

        public void setParameter(HashMap<String, String> parameter) {
            this.parameter = parameter;
        }

        public String mapParameters(String content){

            for(String key : parameter.keySet()){
                content = content.replace("[" + key + "]", parameter.get(key));
            }

            return content;

        }
    }
}

