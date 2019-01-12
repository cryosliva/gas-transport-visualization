package com.gas.transport.visualisation

import com.gas.transport.visualisation.application.GasTransportVisualisationApplication
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * Базовый класс для тестов
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [GasTransportVisualisationApplication::class])
abstract class AbstractTest
