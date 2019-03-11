package com.gas.transport.visualisation.web.dto

import com.gas.transport.visualisation.model.entity.UserRole

class UserSetRolesDto(val username: String, val roles: List<UserRole>)