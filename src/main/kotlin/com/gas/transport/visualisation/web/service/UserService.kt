package com.gas.transport.visualisation.web.service

import com.gas.transport.visualisation.model.dao.UserDao
import com.gas.transport.visualisation.model.entity.User
import com.gas.transport.visualisation.model.entity.UserRole
import com.gas.transport.visualisation.util.Assert
import com.gas.transport.visualisation.util.SecurityUtils
import com.gas.transport.visualisation.util.notNull
import com.gas.transport.visualisation.web.dto.UserChangeInfoDto
import com.gas.transport.visualisation.web.dto.UserDto
import com.gas.transport.visualisation.web.dto.UserRegistrationDto
import com.gas.transport.visualisation.web.dto.toUserDto
import org.apache.catalina.security.SecurityUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService {

    @Autowired
    private lateinit var userDao: UserDao

    @Autowired
    private lateinit var encoder: BCryptPasswordEncoder


    @Transactional(readOnly = true)
    fun getUserInfo() = SecurityUtils.getCurrentUserRoles()

    @Transactional
    fun changeUserInfo(dto: UserChangeInfoDto){
        val userId = SecurityUtils.getCurrentUserDetails().id
        val user = userDao.findById(userId).get().notNull("Пользователь не найден")

        if(dto.name!=null)
            user.username = dto.name

        if(dto.password!=null)
            user.password = encoder.encode(dto.password)
    }
}