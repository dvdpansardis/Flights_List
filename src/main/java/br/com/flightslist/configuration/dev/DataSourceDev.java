package br.com.flightslist.configuration.dev;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

import br.com.flightslist.configuration.AbstractDataSource;

/**
 * This class configure the DataSource using DEV Profile, when the server run
 * using "-Dspring.profiles.active=dev" parameter to use this profile and this
 * parameter "-Dsettings.ip.database=xxx.xxx.xxx.xxx" to set the IP of database.
 * 
 * @see <code>br.com.flightslist.configuration.AbstractDataSource</code>
 * 
 * @author David Pansardis
 * @version 1.0
 *
 */
@Configuration
@Profile("dev")
public class DataSourceDev extends AbstractDataSource {

	/**
	 * Configure the LocalSessionFactoryBuilder with settings to the Hibernate.
	 * Include:
	 * 	-Update data base model
	 * 
	 * @return LocalSessionFactoryBuilder Bean
	 */
	@Bean
	@Override
	public LocalSessionFactoryBuilder sessionFactoryBuilder() {
		LocalSessionFactoryBuilder sfb = new LocalSessionFactoryBuilder(dataSource());
		sfb.scanPackages("br.com.flightslist.domain");

		Properties properties = new Properties();

		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		
		sfb.addProperties(properties);
		
		return sfb;
	}

}
