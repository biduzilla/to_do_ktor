package com.ricky.routing

import com.ricky.model.Task
import com.ricky.service.TaskService
import com.ricky.utils.getParameter
import com.ricky.utils.toPriorityOrNull
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.taskRoute(taskService: TaskService) {
    route("/task") {
        get {
            val tasks = taskService.allTasks()
            call.respond(tasks)
        }
        get("/{id}") {
            val id = call.getParameter("id").toLong()
            val task = taskService.getById(id)
            call.respond(task)
        }
        get("/by-user/{id}") {
            val id = call.getParameter("id").toLong()
            val tasks = taskService.getByUserId(id)
            call.respond(tasks)
        }
        get("/by-priority/{priority}") {
            val priority = call.getParameter("id").toPriorityOrNull()
            priority?.let {
                val tasks = taskService.taskByPriority(priority)
                call.respond(tasks)
            } ?: call.respond(HttpStatusCode.BadRequest)
        }
        get("/by-name/{name}") {
            val name = call.getParameter("name")
            val tasks = taskService.getByName(name)
            call.respond(tasks)
        }
        post {
            val task = call.receive<Task>()
            val save = taskService.addTask(task)
            call.respond(HttpStatusCode.Created, save)
        }
        delete("/{id}") {
            val id = call.getParameter("id").toLong()
            taskService.removeTask(id)
            call.respond(HttpStatusCode.NoContent)
        }
    }
}