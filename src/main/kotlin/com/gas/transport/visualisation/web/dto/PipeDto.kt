package com.gas.transport.visualisation.web.dto

import com.gas.transport.visualisation.model.entity.Pipe

class PipeDto(val capacity: Double, val source: NodeDto, val destination: NodeDto)

fun Pipe.toPipeDto(): PipeDto = PipeDto(capacity!!, source!!.toNodeDto(), destination!!.toNodeDto())