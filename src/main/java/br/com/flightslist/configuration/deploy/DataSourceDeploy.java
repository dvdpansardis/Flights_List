package br.com.flightslist.configuration.deploy;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

import br.com.flightslist.configuration.AbstractDataSource;

/**
 * This class configure the DataSource using DEPLOY Profile, when the server run
 * using "-Dspring.profiles.active=deploy" parameter to use this profile and
 * this parameter "-Dsettings.ip.database=xxx.xxx.xxx.xxx" to set the IP of
 * database.
 * 
 * @see <code>br.com.flightslist.configuration.AbstractDataSource</code>
 * 
 * @author David Pansardis
 * @version 1.0
 *
 */
@Configuration
@Profile("deploy")
public class DataSourceDeploy extends AbstractDataSource {

	/**
	 * Configure the LocalSessionFactoryBuilder with settings to the Hibernate.
	 * Include: -Auto create data base model -Import initial data script, on the
	 * path "src/main/resource/database/import.sql"
	 * 
	 * @return LocalSessionFactoryBuilder Bean
	 */
	@Bean
	public LocalSessionFactoryBuilder sessionFactoryBuilder() {
		LocalSessionFactoryBuilder sfb = new LocalSessionFactoryBuilder(dataSource());
		sfb.scanPackages("br.com.flightslist.domain");

		Properties properties = new Properties();

		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.hbm2ddl.import_files", "database/import.sql");

		sfb.addProperties(properties);

		return sfb;
	}

}
