package com.gas.transport.visualisation.model.entity

import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractMapEntity : AbstractEntity() {

    @Column(name = "snapshot_id", updatable = false)
    var snapshotId: String? = null

    @Column(name = "year", updatable = false)
    var year: Int? = null
}