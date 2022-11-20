package com.animals.WebService.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EntityScan("com.animals.WebService.heroku.model")
@ComponentScan({"com.animals.WebService.heroku.controller", "com.animals.WebService.heroku.service"})
@EnableJpaRepositories(
        entityManagerFactoryRef = "herokuEntityManagerFactory",
        transactionManagerRef = "herokuTransactionManager",
        basePackages = {"com.animals.WebService.heroku.repository"}
)
public class HerokuDatabaseConfig {

    @Bean(name = "herokuDataSource")
    @ConfigurationProperties(prefix = "herokudatabase.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "herokuEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("herokuDataSource") DataSource dataSource
    ) {
        String[] packagesArray = "com.animals.WebService.heroku.controller,com.animals.WebService.heroku.service,com.animals.WebService.heroku.model".split(",");
        return builder
                .dataSource(dataSource)
                .packages(packagesArray)
                .persistenceUnit("heroku")
                .properties(additionalJpaProperties())
                .build();
    }

    Map<String, ?> additionalJpaProperties() {
        Map<String, String> map = new HashMap<>();

        map.put("hibernate.format_sql", "true");
        map.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL81Dialect");
        map.put("hibernate.ddl-auto", "update");

        return map;
    }


    @Bean(name = "herokuTransactionManager")
    public JpaTransactionManager transactionManager(
            @Qualifier("herokuEntityManagerFactory") EntityManagerFactory
                    entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
