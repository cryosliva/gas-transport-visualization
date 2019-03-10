package com.gas.transport.visualisation.web.service

import com.gas.transport.visualisation.model.dao.UserDao
import com.gas.transport.visualisation.model.entity.User
import com.gas.transport.visualisation.model.entity.UserRole
import com.gas.transport.visualisation.util.Assert
import com.gas.transport.visualisation.util.notNull
import com.gas.transport.visualisation.web.dto.UserDto
import com.gas.transport.visualisation.web.dto.UserRegistrationDto
import com.gas.transport.visualisation.web.dto.UserSetRolesDto
import com.gas.transport.visualisation.web.dto.toUserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminService {

    @Autowired
    private lateinit var encoder: BCryptPasswordEncoder

    @Autowired
    private lateinit var userDao: UserDao

    @Transactional
    fun register(dto: UserRegistrationDto): UserDto {
        validateUser(dto)

        val user = User()
        user.password = encoder.encode(dto.password)
        user.username = dto.username
        user.roles.add(UserRole.USER)
        return userDao.save(user).toUserDto()
    }

    private fun validateUser(dto: UserRegistrationDto) {
        val user = userDao.findByUsername(dto.username)
        Assert.notFound(user, "Пользователь с данным логином уже существует")
    }

    @Transactional
    fun setUserRoles(dto: UserSetRolesDto) {
        val user = userDao.findByUsername(dto.username).notNull("Пользователя не существует")
        user.roles.clear()
        user.roles.addAll(dto.roles)
    }

    @Transactional
    fun makeAdmin(username: String) {
        val user = userDao.findByUsername(username).notNull("Пользователя не существует")
        if(!user.roles.contains(UserRole.ADMIN))
            user.roles.add(UserRole.ADMIN)
    }
}