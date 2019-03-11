package com.gas.transport.visualisation.web.controller

import com.gas.transport.visualisation.web.dto.UserChangeInfoDto
import com.gas.transport.visualisation.web.dto.UserRegistrationDto
import com.gas.transport.visualisation.web.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/user/info")
    fun getInfo() = userService.getUserInfo()

    @PostMapping("/user/info")
    fun changeInfo(@RequestBody dto: UserChangeInfoDto) = userService.changeUserInfo(dto)
}