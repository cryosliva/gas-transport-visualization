package com.gas.transport.visualisation.web.controller

import com.gas.transport.visualisation.web.dto.NodeDto
import com.gas.transport.visualisation.web.dto.filter.NodeFilterDto
import com.gas.transport.visualisation.web.service.NodeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class NodeController {

    @Autowired
    lateinit var nodeService: NodeService

    @GetMapping("/node/all")
    fun getAllNodes(): List<NodeDto> = nodeService.getAllNodes()

    @PostMapping("/node/filter")
    fun getFilteredNodes(@RequestBody filter: NodeFilterDto) = nodeService.getFilteredNodes(filter)
}