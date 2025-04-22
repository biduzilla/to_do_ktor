package com.ricky.plugins

import com.ricky.exception.GenericServerError
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import kotlinx.serialization.Serializable


fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            when (cause) {
                is GenericServerError -> {
                    call.respond(HttpStatusCode.fromValue(cause.httpStatusCode), cause)
                }

                is RequestValidationException -> {
                    call.respond(HttpStatusCode.BadRequest, GenericServerError(
                        httpStatusCode = HttpStatusCode.BadRequest.value,
                        errorMessage = cause.reasons.toString(),
                        errorCode = HttpStatusCode.BadRequest.description
                    ))
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