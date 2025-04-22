package com.ricky.validation

import com.ricky.model.User
import io.ktor.server.plugins.requestvalidation.*

fun RequestValidationConfig.userValidation() {
    validate<User> { user ->
        val violations = buildList {
            user.apply {
                when {
                    userName.isBlank() -> add("Nome de usuário é obrigatório")
                    userName.length < 3 -> add("Nome deve ter pelo menos 3 caracteres")
                    userName.length > 50 -> add("Nome não pode exceder 50 caracteres")
                    !userName.matches(Regex("^[a-zA-Z0-9._-]+$")) ->
                        add("Nome só pode conter letras, números, pontos, hífens e underscores")
                }

                when {
                    password.isBlank() -> add("Senha é obrigatória")
                    password.length < 8 -> add("Senha deve ter pelo menos 8 caracteres")
                    password.length > 128 -> add("Senha não pode exceder 128 caracteres")
                    !password.any(Char::isDigit) ->
                        add("Senha deve conter pelo menos 1 número")

                    !password.any(Char::isUpperCase) ->
                        add("Senha deve conter pelo menos 1 letra maiúscula")

                    !password.any { it in "!@#$%^&*()_+" } ->
                        add("Senha deve conter pelo menos 1 caractere especial")
                }

            }

        }

        violations.takeIf { it.isNotEmpty() }?.let {
            ValidationResult.Invalid(it.joinToString(separator = " | "))
        } ?: ValidationResult.Valid
    }
}