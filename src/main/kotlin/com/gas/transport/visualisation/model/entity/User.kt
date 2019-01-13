package com.gas.transport.visualisation.model.entity

import javax.persistence.*

@Entity
@Table(name = "gtv_user")
class User : AbstractEntity() {
    @Column(name = "username", unique = true)
    var username: String? = null

    @Column(name = "password")
    var password: String? = null

    @Column(name = "non_expired")
    var isAccountNonExpired = true

    @Column(name = "non_locked")
    var isAccountNonLocked = true

    @Column(name = "credentials_non_expired")
    var isCredentialsNonExpired = true

    @Column(name = "enabled")
    var isEnabled = true

    @ElementCollection(fetch = FetchType.EAGER) // думаю роли будут достаточно часто нужны
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "gtv_user_role", joinColumns = [JoinColumn(name = "user_id")])
    @Column(name = "user_role")
    val roles = mutableListOf<UserRole>()
}