package com.ricky.validation

import com.ricky.model.Priority
import com.ricky.model.Task
import io.ktor.server.plugins.requestvalidation.*

fun RequestValidationConfig.taskValidation() {
    validate<Task> { task ->
        val violations = buildList {
            task.apply {
                when {
                    name.isBlank() -> add("Nome da tarefa é obrigatório")
                    name.length < 3 -> add("Nome muito curto (mínimo 3 caracteres)")
                    name.length > 100 -> add("Nome muito longo (máximo 100 caracteres)")
                }

                description?.let { desc ->
                    if (desc.length > 500) add("Descrição muito longa (máximo 500 caracteres)")
                }

                if (task.priority !in Priority.entries.toTypedArray()) {
                    add("Prioridade inválida: ${task.priority}")
                }
            }
        }
        violations.takeIf { it.isNotEmpty() }?.let {
            ValidationResult.Invalid(it.joinToString(separator = " | "))
        } ?: ValidationResult.Valid
    }
}