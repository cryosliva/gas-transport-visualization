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
                    .username("uvstxzqprhqcyl")
                    .password("a95e971bac8f9dc0473ba04663fefd89d334befd06a6c3e7c4ec23c14612e329")
                    .driverClassName("org.postgresql.Driver")
                    .url("jdbc:postgresql://ec2-54-247-85-251.eu-west-1.compute.amazonaws.com:5432/d6uptcffdhnorv")
                    .build()
}