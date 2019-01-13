package com.gas.transport.visualisation.util.error.handler

import com.gas.transport.visualisation.util.error.ErrorDetails
import com.gas.transport.visualisation.util.error.type.GtvAccessDeniedException
import com.gas.transport.visualisation.util.error.type.GtvNotFoundException
import com.gas.transport.visualisation.util.error.type.GtvRuntimeException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class DefaultGtvControllerExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(GtvNotFoundException::class)
    fun handleGtvNotFoundException(ex: GtvNotFoundException, request: WebRequest): ResponseEntity<ErrorDetails> {
        val errorDetails = ErrorDetails(ex.message.orEmpty(), ex.errorCode, request.getDescription(false))
        return ResponseEntity(errorDetails, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(GtvAccessDeniedException::class)
    fun handleGtvAccessDeniedException(ex: GtvAccessDeniedException, request: WebRequest): ResponseEntity<ErrorDetails> {
        val errorDetails = ErrorDetails(ex.message.orEmpty(), ex.errorCode, request.getDescription(false))
        return ResponseEntity(errorDetails, HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(GtvRuntimeException::class)
    fun handleGtvRuntimeException(ex: GtvRuntimeException, request: WebRequest): ResponseEntity<ErrorDetails> {
        val errorDetails = ErrorDetails(ex.message.orEmpty(), ex.errorCode, request.getDescription(false))
        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
    }
}