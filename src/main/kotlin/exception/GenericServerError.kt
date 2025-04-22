package com.ricky.exception

import kotlinx.serialization.Serializable

@Serializable
data class GenericServerError(
    val httpStatusCode: Int,
    val errorMessage: String,
    val errorCode: String? = null
) : RuntimeException(errorMessage)
