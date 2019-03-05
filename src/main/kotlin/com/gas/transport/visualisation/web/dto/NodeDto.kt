package com.gas.transport.visualisation.web.dto

import com.gas.transport.visualisation.model.entity.Node

class NodeDto(val latitude: Double,
              val longitude: Double,
              val name: String,
              val region: String,
              val type: String)

fun Node.toNodeDto(): NodeDto = NodeDto(latitude!!, longitude!!, name!!, region!!, type!!.name)
