package com.ricky.model

import com.ricky.dto.TaskDTO
import kotlinx.serialization.Serializable

enum class Priority {
    Low, Medium, High, Vital
}

@Serializable
data class Task(
    val idTask: Long?=null,
    val idUser: Long,
    val name: String,
    val description: String?,
    val completed: Boolean,
    val priority: Priority
) {
    fun toDTO(user: User): TaskDTO {
        return TaskDTO(
            idTask = idTask?:0L,
            user = user,
            name = name,
            description = description,
            completed = completed,
            priority = priority
        )
    }
}
