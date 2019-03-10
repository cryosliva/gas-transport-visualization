package com.gas.transport.visualisation.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(basePackages = ["com.gas.transport.visualisation.model.dao"])
@EntityScan(basePackages = ["com.gas.transport.visualisation.model.entity"])
class JpaConfig {
    @Bean
    @Profile("dev")
    fun devDataSource(): DataSource =
            DataSourceBuilder.create()
                    .username("postgres")
                    .password("12345")
                    .driverClassName("org.postgresql.Driver")
                    .url("jdbc:postgresql://localhost:5432/gtv")
                    .build()

    @Bean
    @Profile("prod")
    fun prodDataSource(): DataSource =
            DataSourceBuilder.create()
                    .username("gdgazkfsqkipcp")
                    .password("3a68ead98f1799698530e0d2ecc51c0dab03a7db9ea0e8e3cb301fca2d42b004")
                    .driverClassName("org.postgresql.Driver")
                    .url("jdbc:postgresql://ec2-54-247-70-127.eu-west-1.compute.amazonaws.com:5432/dmbqk4e9og41g")
                    .build()
}