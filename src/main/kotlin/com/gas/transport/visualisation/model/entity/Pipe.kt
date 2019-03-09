package com.gas.transport.visualisation.model.entity

import javax.persistence.*

@Entity
@Table(name = "gtv_pipe")
class Pipe : AbstractMapEntity() {
    @Column(name = "capacity")
    var capacity: Double = 0.0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id", nullable = false)
    var destination: Node? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id", nullable = false)
    var source: Node? = null
}