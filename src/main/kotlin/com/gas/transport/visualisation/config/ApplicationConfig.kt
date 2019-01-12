package com.gas.transport.visualisation.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["com.gas.transport.visualisation.web", "com.gas.transport.visualisation.util.error.handler","com.gas.transport.visualisation.security"])
class ApplicationConfig