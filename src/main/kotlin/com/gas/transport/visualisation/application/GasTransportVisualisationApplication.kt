package com.gas.transport.visualisation.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.gas.transport.visualisation.config"])
class GasTransportVisualisationApplication

fun main(args: Array<String>) {
    runApplication<GasTransportVisualisationApplication>(*args)
}
