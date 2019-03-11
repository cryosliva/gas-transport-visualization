package com.gas.transport.visualisation.web.controller

import com.gas.transport.visualisation.web.dto.filter.NodeFilterDto
import com.gas.transport.visualisation.web.service.MapService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.xml.ws.Service

@RestController
class MapController {

    @Autowired
    private lateinit var mapService: MapService

    @PostMapping("/map/filter")
    fun getMap(@RequestBody filter: NodeFilterDto) = mapService.getMap(filter)
}