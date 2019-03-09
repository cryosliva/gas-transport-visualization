package com.gas.transport.visualisation.model.dao.criteria

import com.gas.transport.visualisation.model.entity.Pipe
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface PipeCustomDao: JpaRepository<Pipe, Long>, JpaSpecificationExecutor<Pipe>

fun hasPipeYear(year: Int) = Specification<Pipe> { root, _, criteriaBuilder -> criteriaBuilder.equal(root.get<Int>("year"), year) }

fun hasPipeSnapshot(snapshotId: String) = Specification<Pipe> { root, _, criteriaBuilder -> criteriaBuilder.equal(root.get<String>("snapshot_id"), snapshotId) }

