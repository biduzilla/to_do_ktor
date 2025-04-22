package com.ricky.routing

import com.ricky.model.User
import com.ricky.service.UserService
import com.ricky.utils.getParameter
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes(userService: UserService) {
    route("/users") {
        get {
            val users = userService.getAll()
            call.respond(users)
        }
        get("/{id}") {
            val id = call.getParameter("id").toLong()
            val user = userService.getById(id)
            call.respond(user)
        }
        get("/by-name/{name}") {
            val name = call.getParameter("name")

            val user = userService.getUserByName(name)
            call.respond(user)
        }
        post {
            val user = call.receive<User>()
            val userSave = userService.addUser(user)
            call.respond(HttpStatusCode.Created, userSave)
        }
        delete("/{id}") {
            val id = call.getParameter("id").toLong()
            userService.removeUser(id)
            call.respond(HttpStatusCode.NoContent)
        }
    }
}