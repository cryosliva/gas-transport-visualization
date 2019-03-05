package com.gas.transport.visualisation.util

import com.gas.transport.visualisation.model.entity.NodeType
import com.gas.transport.visualisation.util.error.ErrorCode
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Sheet

object ExcelConstants {
    // sheets
    const val nodeInfoSheet = "nodes_locations"
    const val pipeInfoSheet = "flows"

    // columns
    val nodeName = "Node"
    val nodeLatitude = "Latitude"
    val nodeLongitude = "Longitude"
    val nodeType = "Type"
    val nodeRegion = "Region"
    val nodeInfoHeaders = listOf(nodeName, nodeLatitude, nodeLongitude, nodeType, nodeRegion)
}

fun Sheet.getColumnToIndexMap(): MutableMap<String, Int> {
    val headerToIndexMap = mutableMapOf<String, Int>()

    val headerRow = this.getRow(0)
    headerRow.forEachIndexed { index, cell ->
        if (ExcelConstants.nodeInfoHeaders.contains(cell.stringCellValue.trim())) {
            headerToIndexMap[cell.stringCellValue.trim()] = index
        }
    }

    Assert.isTrue(ExcelConstants.nodeInfoHeaders.size == headerToIndexMap.size, ErrorCode.INCORRECT_VALUE, "Некорректный формат листа ${ExcelConstants.nodeInfoSheet}")
    return headerToIndexMap
}

fun String.getPosition() = this.substringBefore(' ').toDouble()

fun String.getNodeType(): NodeType = when (this.trim()) {
    "Месторождение" -> NodeType.FIELD
    "КС" -> NodeType.CS
    "ПХГ" -> NodeType.UGS
    "Город" -> NodeType.CITY
    "ГРЭС" -> NodeType.SDPS
    "ГРС" -> NodeType.GDS
    "ГПЗ" -> NodeType.GPP
    "ГИС" -> NodeType.GMS
    else -> {
        Assert.notFound(this, "Неизвестный тип станции")
        NodeType.UNKNOWN
    }
}

fun Cell.safeStringValue() = when (this.cellTypeEnum) {
    CellType.ERROR -> ""
    else -> this.stringCellValue.trim()
}

inline fun <reified T> T?.notNull() : T{
    Assert.isTrue(this != null, ErrorCode.INCORRECT_VALUE, "Некорректный формат загруженной страницы : $this")
    return this!!
}

