package br.com.flightslist.configuration;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

/**
 * This Abstract class has the generation of DataSource Bean. Used to create a
 * new DataBase profile.
 * 
 * @author David Pansardis
 * @version 1.0
 */
@Configuration
public abstract class AbstractDataSource {

	/**
	 * Configure the DataSource with the settings to conect on Postgres
	 * DataBase. Use the "-Dsettings.ip.database=xxx.xxx.xxx.xxx" parameter to
	 * set the local database on startup system.
	 * 
	 * @return DataSource Bean
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource manager = new DriverManagerDataSource();

		manager.setDriverClassName("org.postgresql.Driver");
		manager.setUrl("jdbc:postgresql://" + System.getProperty("settings.ip.database") + ":5432/flightslist");
		manager.setUsername("postgres");
		manager.setPassword("postgres");

		return manager;
	}

	@Bean
	public abstract LocalSessionFactoryBuilder sessionFactoryBuilder();

	@Bean
	public SessionFactory sessionFactory() {
		return sessionFactoryBuilder().buildSessionFactory();
	}
}
