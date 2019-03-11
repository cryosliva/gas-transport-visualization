package com.gas.transport.visualisation.model.entity

import javax.persistence.*

@Entity
@Table(name = "gtv_node")
class Node : AbstractMapEntity() {
    @Column(name = "latitude")
    var latitude: Double? = null

    @Column(name = "longitude")
    var longitude: Double? = null

    @Column(name = "name")
    var name: String? = null

    @Column(name = "region")
    var region: String? = null

    @Column(name = "type")
    var type: NodeType? = null

    @Column(name = "supply")
    var supply: Double = 0.0

    @Column(name = "demand")
    var demand: Double = 0.0

    @OneToMany(cascade = [CascadeType.ALL],
            fetch = FetchType.EAGER,
            mappedBy = "destination")
    var demandsSources = mutableSetOf<Pipe>()

    @OneToMany(cascade = [CascadeType.ALL],
            fetch = FetchType.EAGER,
            mappedBy = "source")
    var supplySources = mutableSetOf<Pipe>()
}