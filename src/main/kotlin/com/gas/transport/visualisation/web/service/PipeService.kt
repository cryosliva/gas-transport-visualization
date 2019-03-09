package com.gas.transport.visualisation.web.service

import com.gas.transport.visualisation.model.dao.PipeDao
import com.gas.transport.visualisation.model.dao.criteria.PipeCustomDao
import com.gas.transport.visualisation.model.dao.criteria.hasPipeSnapshot
import com.gas.transport.visualisation.model.dao.criteria.hasPipeYear
import com.gas.transport.visualisation.web.dto.filter.PipeFilteredDto
import com.gas.transport.visualisation.web.dto.toPipeDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PipeService {

    @Autowired
    lateinit var pipeDao: PipeDao

    @Autowired
    lateinit var pipeCustomDao: PipeCustomDao

    fun getAllPipes() = pipeDao.findAll().map { pipe -> pipe.toPipeDto() }.toList()

    fun getFilteredPipes(filter: PipeFilteredDto) = pipeCustomDao.findAll(hasPipeYear(filter.year).and(hasPipeSnapshot(filter.snapshotId))).map { pipe -> pipe.toPipeDto() }

}