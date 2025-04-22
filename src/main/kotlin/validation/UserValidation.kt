package com.ricky.validation

import com.ricky.model.User
import io.ktor.server.plugins.requestvalidation.*

fun RequestValidationConfig.userValidation() {
    validate<User> { user ->
        if (user.userName.isBlank() || user.userName.length < 3) {
            ValidationResult.Invalid("Nome deve ter pelo menos 3 letras")
        } else if (user.password.isBlank() || user.password.length < 8) {
            ValidationResult.Invalid("Senha deve ter pelo menos 8 letras")
        } else {
            ValidationResult.Valid
        }
    }
}