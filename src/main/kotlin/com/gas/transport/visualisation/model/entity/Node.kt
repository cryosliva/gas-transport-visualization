package com.gas.transport.visualisation.model.entity

import javax.persistence.*

@Entity
@Table(name = "gtv_node")
class Node : AbstractEntity() {
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

    @OneToMany(cascade = [CascadeType.ALL],
            fetch = FetchType.LAZY,
            mappedBy = "destination")
    var demandsSources = mutableSetOf<Pipe>()

    @OneToMany(cascade = [CascadeType.ALL],
            fetch = FetchType.LAZY,
            mappedBy = "source")
    var supplySources = mutableSetOf<Pipe>()
}