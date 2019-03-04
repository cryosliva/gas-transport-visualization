package com.gas.transport.visualisation.web.dto

import com.gas.transport.visualisation.model.entity.Pipe

class PipeDto(val capacity: Double, firstPosition: PositionDto, secondPosition: PositionDto)

class PositionDto(val latitude: Double, val longtitude: Double)

fun Pipe.toPipeDto(): PipeDto = PipeDto(capacity!!, source!!.getPositionDto(), destination!!.getPositionDto())