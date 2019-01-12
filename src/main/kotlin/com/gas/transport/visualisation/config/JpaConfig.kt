package com.gas.transport.visualisation.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(basePackages = ["com.gas.transport.visualisation.model.dao"])
@EntityScan(basePackages = ["com.gas.transport.visualisation.model.entity"])
class JpaConfig {
    @Bean
    fun devDataSource(): DataSource =
            DataSourceBuilder.create()
                    .username("postgres")
                    .password("12345")
                    .driverClassName("org.postgresql.Driver")
                    .url("jdbc:postgresql://localhost:5432/gtv")
                    .build()
}