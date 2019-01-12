package com.gas.transport.visualisation.security

import com.gas.transport.visualisation.model.dao.UserDao
import com.gas.transport.visualisation.util.Assert
import com.gas.transport.visualisation.util.error.ErrorCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UsernamePasswordAuthenticationProvider : AuthenticationProvider {

    @Autowired
    private lateinit var userDao: UserDao
    @Autowired
    private lateinit var encoder: BCryptPasswordEncoder


    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name
        val password = authentication.credentials.toString()

        val user = userDao.findByUsername(username)
        // проверка аккаунта
        Assert.found(user, "Неправильные имя пользователя/пароль")
        Assert.isTrue(encoder.matches(password, user!!.password), ErrorCode.ACCESS_DENIED,
                "Неправильные имя пользователя/пароль")
        Assert.isTrue(user.isAccountNonExpired, ErrorCode.ACCESS_DENIED,
                "Срок действия аккаунта истёк")
        Assert.isTrue(user.isAccountNonLocked, ErrorCode.ACCESS_DENIED,
                "Аккаунт заблокирован")
        Assert.isTrue(user.isCredentialsNonExpired, ErrorCode.ACCESS_DENIED,
                "Учетные данные устарели")
        Assert.isTrue(user.isEnabled, ErrorCode.ACCESS_DENIED,
                "Аккаунт не активен")
        // сконвертируем роли
        val authorities = user.roles.map {role->
            SimpleGrantedAuthority("ROLE_${role.name}")
        }.toList()

        return UsernamePasswordAuthenticationToken(user.toUserDetails(), password, authorities)
    }

    override fun supports(authentication: Class<*>)= authentication == UsernamePasswordAuthenticationToken::class.java
}