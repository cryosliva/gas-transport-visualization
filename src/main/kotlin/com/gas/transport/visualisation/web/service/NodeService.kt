package com.gas.transport.visualisation.web.service

import com.gas.transport.visualisation.model.dao.NodeDao
import com.gas.transport.visualisation.model.dao.criteria.*
import com.gas.transport.visualisation.web.dto.NodeDto
import com.gas.transport.visualisation.web.dto.filter.NodeFilterDto
import com.gas.transport.visualisation.web.dto.toNodeDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NodeService {

    @Autowired
    lateinit var nodeDao: NodeDao

    @Autowired
    lateinit var nodeCustomDao: NodeCustomDao

    fun getAllNodes() = nodeDao.findAll().map { node -> node.toNodeDto() }

    fun getFilteredNodes(filter: NodeFilterDto): List<NodeDto> {
        var specification = hasNodeYear(filter.year).and(hasNodeSnapshot(filter.snapshotId))
        if (filter.region != null)
            specification = specification.and(hasRegion(filter.region))

        if (filter.type != null)
            specification = specification.and(hasNodeType(filter.type))

        return nodeCustomDao.findAll(specification).map { node -> node.toNodeDto() }
    }
}