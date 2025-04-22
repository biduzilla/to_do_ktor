package com.ricky

import com.ricky.repository.impl.TaskRepositoryImpl
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val taskRepository = TaskRepositoryImpl()
    configureSerialization()
    configureDatabases()
    configureRouting()
}
