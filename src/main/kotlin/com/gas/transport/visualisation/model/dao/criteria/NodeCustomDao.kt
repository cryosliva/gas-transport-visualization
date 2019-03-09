package com.gas.transport.visualisation.model.dao.criteria

import com.gas.transport.visualisation.model.entity.Node
import com.gas.transport.visualisation.model.entity.NodeType
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor


interface NodeCustomDao : JpaRepository<Node, Long>, JpaSpecificationExecutor<Node>

fun hasNodeYear(year: Int) = Specification<Node> { root, _, criteriaBuilder -> criteriaBuilder.equal(root.get<Int>("year"), year) }

fun hasNodeSnapshot(snapshotId: String) = Specification<Node> { root, _, criteriaBuilder -> criteriaBuilder.equal(root.get<String>("snapshotId"), snapshotId) }

fun hasRegion(region: String) = Specification<Node> { root, _, criteriaBuilder -> criteriaBuilder.equal(root.get<String>("region"), region) }

fun hasNodeType(type: NodeType) = Specification<Node> { root, _, criteriaBuilder -> criteriaBuilder.equal(root.get<String>("type"), type) }