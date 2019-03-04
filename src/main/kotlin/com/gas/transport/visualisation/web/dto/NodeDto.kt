package com.gas.transport.visualisation.web.dto

import com.gas.transport.visualisation.model.entity.Node

class NodeDto(val latitude: Double,
              val longitude: Double,
              val name: String,
              val region: String,
              val type: String,
              val demand: Double,
              val supply: Double)

fun Node.toNodeDto(): NodeDto = NodeDto(latitude!!, longitude!!, name!!, region!!, type!!.name, demand!!, supply!!)

fun Node.getPositionDto(): PositionDto = PositionDto(latitude!!, longitude!!)