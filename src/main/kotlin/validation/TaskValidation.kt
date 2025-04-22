package com.ricky.validation

import com.ricky.model.Task
import io.ktor.server.plugins.requestvalidation.*

fun RequestValidationConfig.taskValidation() {
    validate<Task> { task ->
        if (task.name.isBlank() || task.name.length < 3) {
            ValidationResult.Invalid("Nome deve ter pelo menos 3 letras")
        } else {
            ValidationResult.Valid
        }
    }
}