package com.gas.transport.visualisation.util

import com.gas.transport.visualisation.security.AccountUserDetails
import org.springframework.security.core.context.SecurityContextHolder
import com.gas.transport.visualisation.model.entity.UserRole
import com.gas.transport.visualisation.util.error.type.GtvAccessDeniedException

object SecurityUtils {
    fun getCurrentUserRoles(): Set<UserRole> {
        val principal = SecurityContextHolder.getContext().authentication.principal
        if (principal is AccountUserDetails) {
            return principal.roles
        }
        throw GtvAccessDeniedException("Вы не авторизованы")
    }

    fun getCurrentUserDetails() : AccountUserDetails{
        val principal = SecurityContextHolder.getContext().authentication.principal
        if (principal is AccountUserDetails) {
            return principal
        }
        throw GtvAccessDeniedException("Вы не авторизованы")
    }
}