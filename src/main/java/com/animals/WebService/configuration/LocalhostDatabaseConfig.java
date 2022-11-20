package com.animals.WebService.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EntityScan("com.animals.WebService.localhost.model")
@ComponentScan({"com.animals.WebService.localhost.controller","com.animals.WebService.localhost.service"})
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager",
        basePackages = {"com.animals.WebService.localhost.repository"}
)
public class LocalhostDatabaseConfig {

    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "localhostdatabase.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSource") DataSource dataSource
    ) {
        String[] packagesArray = "com.animals.WebService.localhost.controller,com.animals.WebService.localhost.service,com.animals.WebService.localhost.model".split(",");
        return builder
                .dataSource(dataSource)
                .packages(packagesArray)
                .persistenceUnit("localhost")
                .properties(additionalJpaProperties())
                .build();
    }

    Map<String,?> additionalJpaProperties(){
        Map<String,String> map = new HashMap<>();

        map.put("hibernate.format_sql", "true");
        map.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL81Dialect");
        map.put("hibernate.ddl-auto", "update");

        return map;
    }

    @Primary
    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory
                    entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
