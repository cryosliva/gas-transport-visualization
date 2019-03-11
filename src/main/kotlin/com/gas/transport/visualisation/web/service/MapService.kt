package com.gas.transport.visualisation.web.service

import com.gas.transport.visualisation.model.dao.criteria.*
import com.gas.transport.visualisation.model.entity.Pipe
import com.gas.transport.visualisation.web.dto.MapDto
import com.gas.transport.visualisation.web.dto.filter.NodeFilterDto
import com.gas.transport.visualisation.web.dto.toNodeDto
import com.gas.transport.visualisation.web.dto.toPipeDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MapService {

    @Autowired
    private lateinit var nodeService: NodeService

    fun getMap(filter: NodeFilterDto): MapDto {
        val nodes = nodeService.getUnpreparedFilteredNodes(filter)
        val pipes = mutableListOf<Pipe>()
        val unrelatedPipes = mutableListOf<Pipe>()
        nodes.forEach { node ->
            node.demandsSources.forEach { pipe ->
                if (nodes.contains(pipe.source) && nodes.contains(pipe.destination))
                    pipes.add(pipe)
                else
                    unrelatedPipes.add(pipe)
            }
        }

        return MapDto(nodes.map { node -> node.toNodeDto() }, pipes.distinct().map { pipe -> pipe.toPipeDto() }, unrelatedPipes.map { pipe -> pipe.toPipeDto() })
    }
}