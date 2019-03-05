package com.gas.transport.visualisation.web.service

import com.gas.transport.visualisation.model.dao.PipeDao
import com.gas.transport.visualisation.web.dto.toPipeDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PipeService {

    @Autowired
    lateinit var pipeDao: PipeDao

    fun getAllPipes() = pipeDao.findAll().map { pipe->pipe.toPipeDto() }.toList()

}