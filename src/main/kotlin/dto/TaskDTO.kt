package com.ricky.dto

import com.ricky.model.Priority
import com.ricky.model.Task
import com.ricky.model.User

data class TaskDTO(
    val idTask: Long,
    val user: User,
    val name: String,
    val description: String?,
    val completed: Boolean,
    val priority: Priority
) {
    fun toModel(): Task {
        return Task(
            idTask = idTask,
            idUser = user.idUser?:0L,
            name = name,
            description = description,
            completed = completed,
            priority = priority
        )
    }
}
