package com.gas.transport.visualisation.model.entity

enum class NodeType {
    UNKNOWN,
    FIELD,
    CS, // compress station
    UGS, // underground gas storage
    CITY,
    SDPS, //state district power station
    GMS, //gas measuring station
    GDS, //gas distribution station
    GPP, //gas processing plant
}