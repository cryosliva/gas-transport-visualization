package com.gas.transport.visualisation.web.service

import com.gas.transport.visualisation.model.dao.NodeDao
import com.gas.transport.visualisation.model.dao.PipeDao
import com.gas.transport.visualisation.model.entity.Node
import com.gas.transport.visualisation.model.entity.Pipe
import com.gas.transport.visualisation.util.*
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ExcelWorkbookUploadService {

    @Autowired
    lateinit var nodeDao: NodeDao

    @Autowired
    lateinit var pipeDao: PipeDao

    fun loadExcelBook(snapshotId: String, year: Int, file: MultipartFile) {
        //todo validate
        val workbook = WorkbookFactory.create(file.inputStream)

        // parsing nodes sheet
        val nodeSheet = workbook.getSheet(ExcelConstants.nodeInfoSheet).notNull()
        val headerToIndexMap = nodeSheet.getColumnToIndexMap()

        // removing header row
        nodeSheet.removeRow(nodeSheet.getRow(0))
        val nameToNodeMap = mutableMapOf<String, Node>()

        nodeSheet.forEach { row ->
            val node = Node().apply {
                this.snapshotId = snapshotId
                this.year = year

                this.name = row.getCell(headerToIndexMap[ExcelConstants.nodeName]!!).safeStringValue()
                this.latitude = row.getCell(headerToIndexMap[ExcelConstants.nodeLatitude]!!).safeStringValue().getPosition()
                this.longitude = row.getCell(headerToIndexMap[ExcelConstants.nodeLongitude]!!).safeStringValue().getPosition()
                this.region = row.getCell(headerToIndexMap[ExcelConstants.nodeRegion]!!).safeStringValue()
                this.type = row.getCell(headerToIndexMap[ExcelConstants.nodeType]!!).safeStringValue().getNodeType()
            }
            nameToNodeMap[node.name!!] = node
        }

        // parsing pipes sheet
        val indexToNodeMap = mutableMapOf<Int, Node>()

        val pipeSheet = workbook.getSheet(ExcelConstants.pipeInfoSheet)
        pipeSheet.getRow(0).forEachIndexed { index, cell ->
            if (index != 0) {

                val name = cell.safeStringValue()
                val node = nameToNodeMap[name].notNull()
                indexToNodeMap[index] = node
            }
        }

        // removing header row
        val pipes = mutableListOf<Pipe>()

        pipeSheet.removeRow(pipeSheet.getRow(0))
        pipeSheet.forEachIndexed { _, row ->
            val name = row.getCell(0).safeStringValue()
            val sourceNode = nameToNodeMap[name].notNull()

            row.forEachIndexed { cellIndex, cell ->
                if (cellIndex != 0) {

                    val value = cell.numericCellValue.notNull()
                    if (value.toInt() != 0) {
                        val destinationNode = indexToNodeMap[cellIndex].notNull()
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
        pipes.forEach { pipe ->
            pipe.destination!!.supply += pipe.capacity
            pipe.source!!.demand += pipe.capacity
        }

        nodeDao.deleteAllBySnapshotIdAndYear(snapshotId, year)
        pipeDao.deleteAllBySnapshotIdAndYear(snapshotId, year)

        nodeDao.saveAll(nameToNodeMap.values)
        pipeDao.saveAll(pipes)
    }

}