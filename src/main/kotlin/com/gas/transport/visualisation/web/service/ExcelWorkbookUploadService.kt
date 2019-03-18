package com.gas.transport.visualisation.web.service

import com.gas.transport.visualisation.model.dao.NodeDao
import com.gas.transport.visualisation.model.dao.PipeDao
import com.gas.transport.visualisation.model.entity.Node
import com.gas.transport.visualisation.model.entity.Pipe
import com.gas.transport.visualisation.util.*
import com.gas.transport.visualisation.web.dto.ExcelRemoveDto
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class ExcelWorkbookUploadService {

    @Autowired
    lateinit var nodeDao: NodeDao

    @Autowired
    lateinit var pipeDao: PipeDao

    @Transactional
    fun loadExcelBook(snapshotId: String, year: Int, file: MultipartFile) {
        //todo validate
        val workbook = WorkbookFactory.create(file.inputStream)

        // parsing nodes sheet
        val nodeSheet = workbook.getSheet(ExcelConstants.nodeInfoSheet).notNull("Нет страницы по id - ${ExcelConstants.nodeInfoSheet}")
        val headerToIndexMap = nodeSheet.getColumnToIndexMap()

        // removing header row
        nodeSheet.removeRow(nodeSheet.getRow(0))
        val nameToNodeMap = mutableMapOf<String, Node>()

        nodeSheet.forEachIndexed { index, row ->
            val node = Node().apply {
                this.snapshotId = snapshotId
                this.year = year

                this.name = row.getCell(headerToIndexMap[ExcelConstants.nodeName]!!).safeStringValue()
                this.latitude = row.getCell(headerToIndexMap[ExcelConstants.nodeLatitude]!!).safeStringValue()?.getPosition("Неверное значение Latitude в строке ${index + 1} таблицы ${ExcelConstants.nodeInfoSheet}")
                this.longitude = row.getCell(headerToIndexMap[ExcelConstants.nodeLongitude]!!).safeStringValue()?.getPosition("Неверное значение Longitude в строке ${index + 1} таблицы ${ExcelConstants.nodeInfoSheet}")
                this.region = row.getCell(headerToIndexMap[ExcelConstants.nodeRegion]!!).safeStringValue()
                this.type = row.getCell(headerToIndexMap[ExcelConstants.nodeType]!!).safeStringValue()?.getNodeType()
            }
            nameToNodeMap[node.name!!] = node
        }

        // parsing pipes sheet
        val indexToNodeMap = mutableMapOf<Int, Node>()

        val pipeSheet = workbook.getSheet(ExcelConstants.pipeInfoSheet)
        pipeSheet.getRow(0).forEachIndexed { index, cell ->
            if (index != 0) {

                val name = cell.safeStringValue()
                val node = nameToNodeMap[name].notNull("Неизвестная стация $name не описана на листе ${ExcelConstants.nodeInfoSheet}")
                indexToNodeMap[index] = node
            }
        }

        // removing header row
        val pipes = mutableListOf<Pipe>()

        pipeSheet.removeRow(pipeSheet.getRow(0))
        pipeSheet.forEachIndexed { rowIndex, row ->
            val name = row.getCell(0).safeStringValue()
            val sourceNode = nameToNodeMap[name].notNull("Неизвестная стация $name не описана на листе ${ExcelConstants.nodeInfoSheet}")

            row.forEachIndexed { cellIndex, cell ->
                if (cellIndex != 0) {

                    val value = cell.numericCellValue.notNull("Некорректное значение в строке $rowIndex, столбце $cellIndex")
                    if (value.toInt() != 0) {
                        val destinationNode = indexToNodeMap[cellIndex].notNull("Неизвестная стация направления по индексу $cellIndex для строки $name")
                        val pipe = Pipe().apply {
                            this.snapshotId = snapshotId
                            this.year = year

                            this.capacity = value
                            this.source = sourceNode
                            this.destination = destinationNode
                        }
                        pipes.add(pipe)
                    }
                }
            }
        }

        //demands - supply calc
        val demands = workbook.getSheet(ExcelConstants.demandsInfoSheet)
        demands.forEachIndexed { index, row ->
            if (index != 0) {
                val name = row.getCell(0).safeStringValue()
                val node = nameToNodeMap[name].notNull("Неизвестная стация $name на листе ${ExcelConstants.demandsInfoSheet} не описана на листе ${ExcelConstants.nodeInfoSheet}")
                val value = row.getCell(1).numericCellValue
                val supply: Double
                val demand: Double
                if (value > 0) {
                    supply = 0.0
                    demand = value
                } else {
                    supply = -value
                    demand = 0.0
                }
                node.supply = supply
                node.demand = demand
            }
        }

        nodeDao.deleteAllBySnapshotIdAndYear(snapshotId, year)
        pipeDao.deleteAllBySnapshotIdAndYear(snapshotId, year)

        nodeDao.saveAll(nameToNodeMap.values)
        pipeDao.saveAll(pipes)
    }

    @Transactional
    fun removeBook(filter: ExcelRemoveDto) {
        pipeDao.deleteAllBySnapshotIdAndYear(filter.snapshotId, filter.year)
        nodeDao.deleteAllBySnapshotIdAndYear(filter.snapshotId, filter.year)
    }

}