package com.gas.transport.visualisation.web.dto.filter

import com.gas.transport.visualisation.model.entity.NodeType

class NodeFilterDto(val region: List<String>?, val type: List<NodeType>?, year: Int, snapshotId: String) : AbstractFilterDto(year, snapshotId)