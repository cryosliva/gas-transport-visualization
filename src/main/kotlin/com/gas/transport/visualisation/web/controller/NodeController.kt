package com.gas.transport.visualisation.web.controller

import com.gas.transport.visualisation.web.dto.NodeDto
import com.gas.transport.visualisation.web.service.NodeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/node")
class NodeController {

    @Autowired
    lateinit var nodeService: NodeService

    @GetMapping("/all")
    fun getAllNodes(): List<NodeDto> = nodeService.getAllNodes()
}