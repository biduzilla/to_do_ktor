package com.ricky.service

import com.ricky.model.Priority
import com.ricky.model.Task

interface TaskService {
    suspend fun allTasks(): List<Task>
    suspend fun getById(id: Long): Task
    suspend fun getByUserId(id: Long): List<Task>
    suspend fun taskByPriority(priority: Priority): List<Task>
    suspend fun getByName(name: String): Task
    suspend fun addTask(task: Task):Task
    suspend fun removeTask(id: Long)
}