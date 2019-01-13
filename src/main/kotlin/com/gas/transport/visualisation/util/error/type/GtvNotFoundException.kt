package com.gas.transport.visualisation.util.error.type

import com.gas.transport.visualisation.util.error.ErrorCode

class GtvNotFoundException(message: String): GtvRuntimeException(message, ErrorCode.NOT_FOUND)