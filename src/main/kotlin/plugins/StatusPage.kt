package com.ricky.plugins

import com.ricky.exception.GenericServerError
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            when (cause) {
                is GenericServerError -> {
                    call.respond(cause.httpStatus, cause.errorMessage)
                }
                is RequestValidationException -> {
                    call.respond(HttpStatusCode.BadRequest, cause.reasons.joinToString())
                }
                else -> {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        mapOf("message" to "Erro interno no servidor")
                    )
                    throw cause
                }
            }
        }
    }
}