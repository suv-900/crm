package com.projects.crm.hibernate;

import java.util.Properties;

import javax.sql.DataSource;

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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
public class HibernateConfiguration {
    @Value("${db.url}")
    String url;

    @Value("${db.username}")
    String username;

    @Value("${db.password}")
    String password;

    @Value("${db.driver}")
    String driver;

    @Value("${entitymanager.packagesToScan}")
    String packagesToScan;

    @Value("${hibernate.hbm2ddl.auto}")
    String hbm2ddl;

    @Value("${hibernate.showSQL}")
    String showSQL;

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
        hibernateProperties.put("hibernate.hbm2ddl.auto",hbm2ddl);
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