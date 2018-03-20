package com.puneet.springsecurity.demo.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.logging.Logger;

@Configuration
@ComponentScan(basePackages="com.puneet.springsecurity.demo")
@EnableWebMvc
@PropertySource("classpath:persistence-mysql.properties")
public class MvcConfiguration {

	//setup a variable to hold the persistence-mysql.properties
	@Autowired
	private Environment env;

	//setup a logger for diagnostics
	private Logger logger = Logger.getLogger(getClass().getName());

	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	//define a bean for our security datasource
	@Bean
	public DataSource securityDataSource() {

		//create a connection pool
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

		//set the jdbc driver class
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}

		//log the connection props
		logger.info(">>> jdbc.url= " + env.getProperty("jdbc.url"));
		logger.info(">>> jdbc.user= " + env.getProperty("jdbc.user"));

		//set db connection props
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));

		//set connection pool props
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

		return securityDataSource;
	}

	//need to create a helper - read env property and convert to int
	private int getIntProperty(String propName) {
		String propVal = env.getProperty(propName);

		//now convert to int
		int intPropVal = Integer.parseInt(propVal);

		return intPropVal;
	}
	
	/*@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}*/
}
