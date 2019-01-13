package com.gas.transport.visualisation.model.dao

import com.gas.transport.visualisation.model.entity.User
import org.springframework.data.repository.CrudRepository

interface UserDao : CrudRepository<User, Long> {
    fun findByUsername(username: String): User?
}