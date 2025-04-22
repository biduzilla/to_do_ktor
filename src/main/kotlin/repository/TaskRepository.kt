package com.ricky.repository

import com.ricky.model.Priority
import com.ricky.model.Task

interface TaskRepository {
    suspend fun allTasks(): List<Task>
    suspend fun getById(id: Long): Task?
    suspend fun getByUserId(id: Long): List<Task>
    suspend fun taskByPriority(priority: Priority): List<Task>
    suspend fun taskByName(name: String): Task?
    suspend fun addTask(task: Task)
    suspend fun removeTask(id: Long)

}