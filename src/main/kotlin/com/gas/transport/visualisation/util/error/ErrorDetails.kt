package com.gas.transport.visualisation.util.error

import java.time.LocalDateTime

class ErrorDetails(val message: String, val code: ErrorCode, val description: String, val time: LocalDateTime = LocalDateTime.now())