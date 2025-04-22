package com.ricky.utils

import com.ricky.exception.GenericServerError
import com.ricky.model.Priority
import io.ktor.http.*
import io.ktor.server.routing.*

fun RoutingCall.getParameter(parameter: String): String {
    return this.parameters[parameter] ?: throw GenericServerError(
        statusCode = HttpStatusCode.BadRequest.value,
        errorMessage = "O parâmetro $parameter é obrigatório",
        httpStatus = HttpStatusCode.BadRequest.description
    )
}

fun String.toPriorityOrNull(): Priority? {
    return Priority.entries.firstOrNull { it.name.equals(this, ignoreCase = true) }
}