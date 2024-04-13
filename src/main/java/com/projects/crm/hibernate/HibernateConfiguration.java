package com.projects.crm.hibernate;

import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.validation.constraints.NotNull;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
public class HibernateConfiguration {

    private static final Logger log = LoggerFactory.getLogger(HibernateConfiguration.class);

    @NotNull(message="configuration props:datasource.url cannot be null")
    @Value("${spring.datasource.url}")
    String url;

    @NotNull(message="configuration props:datasource.username cannot be null")
    @Value("${spring.datasource.username}")
    String username;

    @NotNull(message="configuration props:datasource.password cannot be null")
    @Value("${spring.datasource.password}")
    String password;

    @NotNull(message="configuration props:datasource.driver cannot be null")
    @Value("${spring.datasource.driver-class-name}")
    String driver;

   
    @NotNull(message="configuration props:entityManager.packagesToScan cannot be null")
    @Value("${entityManager.packagesToScan}")
    String packagesToScan;
    
    @NotNull(message="configuration props:jpa.propeties.hbm2ddl.auto cannot be null")
    @Value("${spring.jpa.hibernate.ddl-auto}")
    String ddlAuto;
    
    @NotNull(message="configuration props:jpa.propeties.show_sql cannot be null")
    @Value("${spring.jpa.show-sql}")
    String showSQL;

    @NotNull(message="configuration props:jpa.propeties.generate-ddl cannot be null")
    @Value("${spring.jpa.generate-ddl}")
    String generateDDL;

    @Bean
    public DataSource dataSource(){
        log.info("Requesting for dataSource");   
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);

        
        return dataSource;  
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(){
        EntityManagerFactory factory=Persistence.createEntityManagerFactory("crm-persistence-unit");
        return factory;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        log.info("Requesting for sessionFactory.");
        LocalSessionFactoryBean sessionFactoryBean=new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource()); 
        sessionFactoryBean.setPackagesToScan(packagesToScan); 
        
        Properties hibernateProperties=new Properties();
        hibernateProperties.put("hibernate.show_sql",showSQL);
        hibernateProperties.put("hibernate.ddl-auto",ddlAuto);
        hibernateProperties.put("hibernate.generate-ddl",generateDDL);
        sessionFactoryBean.setHibernateProperties(hibernateProperties);
        
        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager(){
        log.info("Requestion for transactionManager");
        HibernateTransactionManager transactionManager=new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}
