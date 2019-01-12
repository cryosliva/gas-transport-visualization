package com.gas.transport.visualisation.util

import com.gas.transport.visualisation.util.error.ErrorCode
import com.gas.transport.visualisation.util.error.type.GtvNotFoundException
import com.gas.transport.visualisation.util.error.type.GtvRuntimeException

object Assert {
    fun found(`object`: Any?, message: String) {
        if (`object` == null) {
            throw GtvNotFoundException(message)
        }
    }

    fun notFound(`object`: Any?, message: String) {
        if (`object` != null) {
            throw GtvRuntimeException(message, ErrorCode.ALREADY_EXISTS)
        }
    }

    fun isTrue(expression: Boolean, code: ErrorCode, message: String) {
        if (!expression) {
            throw GtvRuntimeException(message, code)
        }
    }
}