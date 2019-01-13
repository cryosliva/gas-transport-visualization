package com.gas.transport.visualisation.model.entity

import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "datetime_create", updatable = false)
    var dateTimeCreate = LocalDateTime.now()
}