package com.ricky.model

import kotlinx.serialization.Serializable

enum class Priority {
    Low, Medium, High, Vital
}

@Serializable
data class Task(
    val idTask: Long,
    val idUser: Long,
    val name: String,
    val description: String?,
    val completed: Boolean,
    val priority: Priority
)
