package com.gas.transport.visualisation.model.dao

import com.gas.transport.visualisation.model.entity.Pipe
import org.springframework.data.repository.CrudRepository

interface PipeDao : CrudRepository<Pipe, Long> {
    fun deleteAllBySnapshotIdAndYear(id: String, year: Int)
}