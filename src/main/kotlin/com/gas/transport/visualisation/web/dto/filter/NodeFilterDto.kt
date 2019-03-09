package com.gas.transport.visualisation.web.dto.filter

import com.gas.transport.visualisation.model.entity.NodeType

class NodeFilterDto(val region: String?, val type: NodeType?, year: Int, snapshotId: String) : AbstractFilterDto(year, snapshotId)