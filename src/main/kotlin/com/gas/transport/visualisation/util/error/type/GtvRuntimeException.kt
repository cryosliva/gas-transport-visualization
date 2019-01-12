package com.gas.transport.visualisation.util.error.type

import com.gas.transport.visualisation.util.error.ErrorCode

open class GtvRuntimeException(message: String, val errorCode: ErrorCode) : RuntimeException(message)