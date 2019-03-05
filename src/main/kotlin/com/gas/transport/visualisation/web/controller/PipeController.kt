package com.gas.transport.visualisation.web.controller

import com.gas.transport.visualisation.web.service.PipeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PipeController {

    @Autowired
    lateinit var pipeService: PipeService

    @GetMapping("/pipe/all")
    fun getAllPipes() = pipeService.getAllPipes()
}