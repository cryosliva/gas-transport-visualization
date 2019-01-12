package com.gas.transport.visualisation.web.dto

import com.gas.transport.visualisation.model.entity.User
import com.gas.transport.visualisation.model.entity.UserRole

class UserDto(val name: String, val roles: Array<UserRole>)

fun User.toUserDto() = UserDto(this.username!!, this.roles.toTypedArray())
