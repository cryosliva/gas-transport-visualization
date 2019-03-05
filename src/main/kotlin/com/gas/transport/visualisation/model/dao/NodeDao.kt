package com.gas.transport.visualisation.model.dao

import com.gas.transport.visualisation.model.entity.Node
import org.springframework.data.repository.CrudRepository

interface NodeDao  : CrudRepository<Node, Long>