package com.gas.transport.visualisation.web.service

import com.gas.transport.visualisation.model.dao.NodeDao
import com.gas.transport.visualisation.web.dto.toNodeDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NodeService {

    @Autowired
    lateinit var nodeDao: NodeDao

    fun getAllNodes() = nodeDao.findAll().map { node-> node.toNodeDto() }
}