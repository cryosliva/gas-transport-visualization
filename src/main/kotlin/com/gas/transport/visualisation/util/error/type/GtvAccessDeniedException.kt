package com.gas.transport.visualisation.util.error.type

import com.gas.transport.visualisation.util.error.ErrorCode

class GtvAccessDeniedException(message: String) : GtvRuntimeException(message, ErrorCode.ACCESS_DENIED)