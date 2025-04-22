package com.ricky.repository.impl

import com.ricky.db.TaskDAO
import com.ricky.db.TaskTable
import com.ricky.db.suspendTransaction
import com.ricky.model.Priority
import com.ricky.model.Task
import com.ricky.repository.TaskRepository
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

class TaskRepositoryImpl : TaskRepository {
    override suspend fun allTasks(): List<Task> {
        return suspendTransaction {
            TaskDAO.all().map { it.toTask() }
        }
    }

    override suspend fun getById(id: Long): Task? {
        return suspendTransaction {
            TaskDAO
                .find { TaskTable.id eq id }
                .limit(1)
                .map { it.toTask() }
                .firstOrNull()
        }
    }

    override suspend fun getByUserId(id: Long): List<Task> {
        return suspendTransaction {
            TaskDAO
                .find { TaskTable.idUser eq id }
                .map { it.toTask() }
        }
    }

    override suspend fun taskByPriority(priority: Priority): List<Task> {
        return suspendTransaction {
            TaskDAO
                .find { TaskTable.priority eq priority }
                .map { it.toTask() }
        }
    }

    override suspend fun taskByName(name: String): Task? {
        return suspendTransaction {
            TaskDAO
                .find { TaskTable.name eq name }
                .limit(1)
                .map { it.toTask() }
                .firstOrNull()
        }
    }

    override suspend fun addTask(task: Task): Task {
        return suspendTransaction {
            TaskDAO.new {
                name = task.name
                description = task.description
                priority = task.priority
            }.toTask()
        }
    }

    override suspend fun removeTask(id: Long) {
        suspendTransaction {
            val rowsDeleted = TaskTable.deleteWhere {
                TaskTable.id eq id
            }
            rowsDeleted == 1
        }
    }

    override suspend fun existsByName(name: String): Boolean {
        return suspendTransaction {
            TaskTable
                .select(TaskTable.name eq name)
                .limit(1)
                .count() > 0
        }
    }
}