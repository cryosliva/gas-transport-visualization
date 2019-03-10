package com.gas.transport.visualisation.web.controller

import com.gas.transport.visualisation.web.dto.UserRegistrationDto
import com.gas.transport.visualisation.web.dto.UserSetRolesDto
import com.gas.transport.visualisation.web.service.AdminService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AdminController {

    @Autowired
    private lateinit var adminService: AdminService

    @PostMapping("/admin/register")
    fun registerUser(dto: UserRegistrationDto) = adminService.register(dto)

    @PostMapping("/admin/user/roles")
    fun setUserRoles(dto: UserSetRolesDto) = adminService.setUserRoles(dto)

    @PutMapping("/admin/user/make-admin")
    fun makeAdmin(username:String) = adminService.makeAdmin(username)
}