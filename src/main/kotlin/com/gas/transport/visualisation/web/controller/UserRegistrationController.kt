package com.gas.transport.visualisation.web.controller

import com.gas.transport.visualisation.web.dto.UserDto
import com.gas.transport.visualisation.web.dto.UserRegistrationDto
import com.gas.transport.visualisation.web.service.UserRegistrationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserRegistrationController {

    @Autowired
    private lateinit var userRegistrationService: UserRegistrationService

    @PostMapping("/register")
    fun register(@RequestBody dto: UserRegistrationDto): UserDto {
        return userRegistrationService.register(dto)
    }
}