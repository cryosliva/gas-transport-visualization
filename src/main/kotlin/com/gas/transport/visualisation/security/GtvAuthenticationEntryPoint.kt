package com.gas.transport.visualisation.security

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class GtvAuthenticationEntryPoint : BasicAuthenticationEntryPoint() {
    override fun commence(request: HttpServletRequest, response: HttpServletResponse, authException: AuthenticationException) {
        response.addHeader("WWW-Authenticate", "Basic realm=$realmName")
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        val writer = response.writer
        writer.println("HTTP Status 401 - " + authException.message)
    }



    override fun afterPropertiesSet() {
        realmName = "GTV Realm"
        super.afterPropertiesSet()
    }
}