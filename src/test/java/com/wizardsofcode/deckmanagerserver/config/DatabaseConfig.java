/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 9/9/17                                 
 */

        package com.wizardsofcode.deckmanagerserver.config;

        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.boot.test.context.TestConfiguration;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.ComponentScan;
        import org.springframework.context.annotation.Profile;
        import org.springframework.context.annotation.PropertySource;
        import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
        import org.springframework.core.env.Environment;
        import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
        import org.springframework.jdbc.datasource.DriverManagerDataSource;
        import org.springframework.orm.jpa.JpaTransactionManager;
        import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
        import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.transaction.annotation.EnableTransactionManagement;

        import javax.persistence.EntityManagerFactory;
        import javax.sql.DataSource;
        import java.security.SecureRandom;
        import java.util.Properties;

@TestConfiguration
@PropertySource(value = "classpath:test.properties")
@EnableTransactionManagement
@EnableJpaRepositories( basePackages = {"com.wizardsofcode.deckmanagerserver.model"})
public class DatabaseConfig {


    // The Recommended way to accesing properties is by using the ENvironment Object
    // however as this is just for testing is easier (at least for me) using @Value

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.password}")
    private String databasePassword;

    @Value("${spring.datasource.username}")
    private String databaseUSer;

    @Value("${spring.datasource.driver-class-name}")
    private String driverName;

    @Bean(name = "passwordEncoder")
    public static BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySource(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public static SecureRandom getRandomCreator() { return new SecureRandom();}

    @Bean
    @Profile(value = "test")
    public DataSource datasource(){

        DriverManagerDataSource ds = new DriverManagerDataSource(databaseUrl,databaseUSer,databasePassword);
        ds.setDriverClassName(driverName);

        return ds;
    }

    @Bean(name = "entityManagerFactory")
    @Profile("test")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(Environment env){

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(datasource());
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManager.setPackagesToScan("com.wizardsofcode.deckmanagerserver.model");

        Properties jpaProps = new Properties();

        jpaProps.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        jpaProps.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        jpaProps.put("hibernate.show_sql","true");
        entityManager.setJpaProperties(jpaProps);

        // entityManager.afterPropertiesSet();


        return entityManager;
    }

    @Bean
    @Profile("test")
    public JpaTransactionManager transactionManager(EntityManagerFactory ent){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(ent);
        return transactionManager;
    }

}
