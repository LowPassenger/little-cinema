package cinema.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = "cinema")
public class AppConfig {
    private static final Logger logger = LogManager.getLogger(AppConfig.class);
    private final Environment environment;

    @Autowired
    public AppConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        String dbDriver = environment.getProperty("db.driver");
        String dbUrl = environment.getProperty("db.url");
        String dbUser = environment.getProperty("db.user");
        String dbPassword = environment.getProperty("db.password");
        dataSource.setDriverClassName(dbDriver);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);
        logger.info("Create DataSource Bean. Params: database driver = {}, "
                + "database URL = {}, database User = {}, database password = OK",
                dbDriver, dbUrl, dbUser);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());

        String hibernateShowSql = environment.getProperty("hibernate.show_sql");
        String hibernateDialect = environment.getProperty("hibernate.dialect");
        String hibernateHmbDdlAuto = environment.getProperty("hibernate.hbm2ddl.auto");

        Properties properties = new Properties();
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.hbm2ddl.auto", hibernateHmbDdlAuto);

        factoryBean.setHibernateProperties(properties);
        factoryBean.setPackagesToScan("cinema.model");
        logger.info("SessionFactory creation. Params: show SQL queries = {}, "
                + "Hibernate Dialect = {}, Hibernate hmb2ddl auto = {}",
                hibernateShowSql, hibernateDialect, hibernateHmbDdlAuto);
        return factoryBean;
    }

    @Bean
    public PasswordEncoder getEncoder() {
        logger.debug("Password Encoder creation.");
        return new BCryptPasswordEncoder();
    }
}
