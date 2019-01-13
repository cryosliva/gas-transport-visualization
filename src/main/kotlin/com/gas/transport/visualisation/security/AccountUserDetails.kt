package com.gas.transport.visualisation.security

import com.gas.transport.visualisation.model.entity.User
import com.gas.transport.visualisation.model.entity.UserRole

class AccountUserDetails(val id: Long, val login: String, val roles: Set<UserRole>)

fun User.toUserDetails() = AccountUserDetails(id!!, username!!, roles.toSet())