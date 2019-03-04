package com.gas.transport.visualisation.web.service

import com.gas.transport.visualisation.model.dao.NodeDao
import com.gas.transport.visualisation.model.dao.PipeDao
import com.gas.transport.visualisation.model.entity.Node
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

    fun loadExcelBook(file: MultipartFile) {
        //todo validate

        val workbook = WorkbookFactory.create(file.inputStream)
        val nodeSheet = workbook.getSheet(ExcelConstants.nodeInfoSheet).notNull()
        val headerToIndexMap = nodeSheet.getColumnToIndexMap()

        // removing header row
        nodeSheet.removeRow(nodeSheet.getRow(0))
        val nodes = mutableListOf<Node>()

        nodeSheet.forEach { row ->
            nodes.add(Node().apply {
                this.name = row.getCell(headerToIndexMap[ExcelConstants.nodeName]!!).safeStringValue()
                this.latitude = row.getCell(headerToIndexMap[ExcelConstants.nodeLatitude]!!).safeStringValue().getPosition()
                this.longitude = row.getCell(headerToIndexMap[ExcelConstants.nodeLongitude]!!).safeStringValue().getPosition()
                this.region = row.getCell(headerToIndexMap[ExcelConstants.nodeRegion]!!).safeStringValue()
                this.type = row.getCell(headerToIndexMap[ExcelConstants.nodeType]!!).safeStringValue().getNodeType()
            })
        }
        nodeDao.deleteAll()
        nodeDao.saveAll(nodes)
    }

}