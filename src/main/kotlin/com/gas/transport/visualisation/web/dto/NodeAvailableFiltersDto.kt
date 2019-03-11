package com.gas.transport.visualisation.web.dto

import com.gas.transport.visualisation.model.entity.NodeType

class NodeAvailableFiltersDto(val snapshots: List<String>, val regions: List<String>, val years: List<Int>, val types: List<NodeType>)