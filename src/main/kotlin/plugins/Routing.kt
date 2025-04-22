package com.ricky.plugins

import DependencyContainer
import com.ricky.routing.taskRoute
import com.ricky.routing.userRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(dependencies: DependencyContainer) {
    routing {
        userRoutes(dependencies.userService)
        taskRoute(dependencies.taskService)
    }
}
