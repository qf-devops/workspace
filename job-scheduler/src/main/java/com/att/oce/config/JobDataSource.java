package com.att.oce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by jt316g on 8/15/2017.
 */
@Configuration
public class JobDataSource {

    @Value("${job.history.datasource.driver-class-name}")
    private String driver;
    @Value("${job.history.datasource.url}")
    private String connURL;
    @Value("${job.history.datasource.username}")
    private String userName;
    @Value("${job.history.datasource.password}")
    private String password;

    @Bean(destroyMethod="", name = "jobDatasource")
    public DataSource jobDataSource() {
        DataSourceBuilder factory = DataSourceBuilder
                .create()
                .driverClassName(driver)
                .url(connURL)
                .username(userName)
                .password(password);
        return factory.build();
    }

    @Bean(destroyMethod="", name = "jobJdbcTemplate")
    public JdbcTemplate jobJdbcTemplate() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(jobDataSource());
        return jdbcTemplate;
    }

}
