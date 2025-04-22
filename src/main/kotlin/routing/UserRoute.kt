package com.ricky.routing

import com.ricky.exception.GenericServerError
import com.ricky.model.User
import com.ricky.service.UserService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.userRoutes() {
    val userService by inject<UserService>()
    route("/users") {
        get {
            val users = userService.getAll()
            call.respond(users)
        }
        get("/{id}") {
            val id = call.parameters["id"]?.toLongOrNull() ?: throw GenericServerError(
                httpStatus = HttpStatusCode.BadRequest,
                errorMessage = "O parâmetro ID é obrigatório"
            )

            val user = userService.getById(id)
            call.respond(user)
        }
        get("/by-name/{name}") {
            val name = call.parameters["name"]
                ?: throw IllegalArgumentException("Nome não fornecido")

            val user = userService.getUserByName(name)
            call.respond(user)
        }
        post {
            val user = call.receive<User>()
            val userSave = userService.addUser(user)
            call.respond(HttpStatusCode.Created, userSave)
        }
        delete("/{id}") {
            val id = call.parameters["id"]?.toLongOrNull() ?: throw GenericServerError(
                httpStatus = HttpStatusCode.BadRequest,
                errorMessage = "O parâmetro ID é obrigatório"
            )
            userService.removeUser(id)
            call.respond(HttpStatusCode.NoContent)
        }
    }
}