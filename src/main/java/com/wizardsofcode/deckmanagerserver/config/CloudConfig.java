/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/2/17                                 
 */

package com.wizardsofcode.deckmanagerserver.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.context.config.annotation.EnableContextRegion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@EnableContextRegion(region = "us-east-1")
public class CloudConfig {


    @Value("${cloud.aws.credentials.accessKey}")
    private String acceskey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretkey;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySource(){
        return new PropertySourcesPlaceholderConfigurer();
    }


    @Autowired
    @Bean("awsCredentials")
    public AWSCredentialsProvider awsCredentials(Environment env){
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(System.getenv().get("AWS_CLIENT")
                ,System.getenv().get("AWS_SECRET")));
    }

    @Autowired
    @Bean
    public AmazonS3 amazonS3(AWSCredentialsProvider awsCredentials){
       return AmazonS3ClientBuilder.standard().withRegion("us-east-1")
                .withCredentials(awsCredentials).build();
    }

    @Autowired
    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService(AWSCredentialsProvider awsCredentials){
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withRegion("us-east-1")
                .withCredentials(awsCredentials).build();
    }

}
