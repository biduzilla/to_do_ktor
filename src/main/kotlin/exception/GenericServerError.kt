package com.ricky.exception

import io.ktor.http.*

data class GenericServerError(
    val httpStatus: HttpStatusCode,
    val errorMessage: String
) : RuntimeException(errorMessage)
