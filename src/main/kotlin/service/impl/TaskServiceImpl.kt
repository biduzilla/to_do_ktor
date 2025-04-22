package com.ricky.service.impl

import com.ricky.exception.GenericServerError
import com.ricky.model.Priority
import com.ricky.model.Task
import com.ricky.repository.TaskRepository
import com.ricky.service.TaskService
import io.ktor.http.*

class TaskServiceImpl(private val taskRepository: TaskRepository) : TaskService {
    override suspend fun allTasks(): List<Task> {
        return taskRepository.allTasks()
    }

    override suspend fun getById(id: Long): Task {
        return taskRepository.getById(id) ?: throw GenericServerError(
            statusCode = HttpStatusCode.NotFound.value,
            errorMessage = "Tarefa com ID $id não encontrado",
            httpStatus = HttpStatusCode.NotFound.description
        )
    }

    override suspend fun getByUserId(id: Long): List<Task> {
        return taskRepository.getByUserId(id)
    }

    override suspend fun taskByPriority(priority: Priority): List<Task> {
        return taskRepository.taskByPriority(priority)
    }

    override suspend fun getByName(name: String): Task {
        return taskRepository.taskByName(name) ?: throw GenericServerError(
            statusCode = HttpStatusCode.NotFound.value,
            errorMessage = "Tarefa com nome $name não encontrado",
            httpStatus = HttpStatusCode.NotFound.description
        )
    }

    override suspend fun addTask(task: Task) {
        if(taskRepository.existsByName(task.name)){
            throw GenericServerError(
                statusCode = HttpStatusCode.BadRequest.value,
                errorMessage = "Tarefa com nome $task.name já cadastrado",
                httpStatus = HttpStatusCode.BadRequest.description
            )
        }
        return addTask(task)
    }

    override suspend fun removeTask(id: Long) {
        taskRepository.removeTask(id)
    }
}