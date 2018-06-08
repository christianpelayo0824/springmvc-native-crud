/*
 * File: HibernateConfig
 * 
 * In this class we can see the Hibernate properties,
 * JDBC properties which we can find in DataSource.
 * 
 * */

package com.webapp.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.webapp.configuration" })
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfig {

	@Autowired
	private Environment env;

	//For Debbuging purposes
	public void getStatus() {
		System.out.println("JDBC");
		System.out.println("Driver: " + env.getProperty("jdbc.driver"));
		System.out.println("Url: " + env.getProperty("jdbc.url"));
		System.out.println("User: " + env.getProperty("jdbc.user"));
		System.out.println("Pass: " + env.getProperty("jdbc.pass"));

		System.out.println("Hibernate");
		System.out.println("hibernate.dialect: " + env.getProperty("hibernate.dialect"));
		System.out.println("hibernate.show_sql: " + env.getProperty("hibernate.show_sql"));
		System.out.println("hibernate.hbm2ddl.auto: " + env.getProperty("hibernate.hbm2ddl.auto"));
	}

	
	//Configure Session factory
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.webapp.entity" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		getStatus();
		return sessionFactory;
	}

	//Configure datasource which cover the jdbc property
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driver"));
		dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(env.getRequiredProperty("jdbc.user"));
		dataSource.setPassword(env.getRequiredProperty("jdbc.pass"));
		return dataSource;
	}

	//Configure hibernate properties
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		return properties;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}

}
