package com.gas.transport.visualisation.web.service

import com.gas.transport.visualisation.model.dao.UserDao
import com.gas.transport.visualisation.model.entity.User
import com.gas.transport.visualisation.model.entity.UserRole
import com.gas.transport.visualisation.util.Assert
import com.gas.transport.visualisation.util.SecurityUtils
import com.gas.transport.visualisation.web.dto.UserDto
import com.gas.transport.visualisation.web.dto.UserRegistrationDto
import com.gas.transport.visualisation.web.dto.toUserDto
import org.apache.catalina.security.SecurityUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    private lateinit var userDao: UserDao

    @Autowired
    private lateinit var encoder: BCryptPasswordEncoder

    fun register(dto: UserRegistrationDto): UserDto {
        validateUser(dto)

        val user = User()
        user.password = encoder.encode(dto.password)
        user.username = dto.username
        user.roles.add(UserRole.USER)
        return userDao.save(user).toUserDto()
    }

    fun getUserInfo() = SecurityUtils.getCurrentUserRoles()

    private fun validateUser(dto: UserRegistrationDto){
        val user = userDao.findByUsername(dto.username)
        Assert.notFound(user, "Пользователь с данным логином уже существует")
    }
}