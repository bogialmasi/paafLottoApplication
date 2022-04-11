package spring.database.config.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class DataSourceConfigurationImpl {
    @Bean
    DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://${MYSQL_HOST:localhost}:3306/db_example");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }

    JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
