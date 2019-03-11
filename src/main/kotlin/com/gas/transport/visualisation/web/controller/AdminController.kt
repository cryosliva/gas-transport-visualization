package com.gas.transport.visualisation.web.controller

import com.gas.transport.visualisation.web.dto.UserRegistrationDto
import com.gas.transport.visualisation.web.dto.UserSetRolesDto
import com.gas.transport.visualisation.web.service.AdminService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

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

    @GetMapping("/admin/user/list")
    fun userList() = adminService.getUserList()

    @PutMapping("/admin/user/delete")
    fun deleteUser(@RequestParam("name") name:String) = adminService.removeUser(name)
}