package com.ricky.exception

import kotlinx.serialization.Serializable

@Serializable
data class GenericServerError(
    val statusCode: Int,
    val errorMessage: String,
    val httpStatus: String? = null
) : RuntimeException(errorMessage)
