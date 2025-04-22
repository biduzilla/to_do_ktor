package com.ricky

import DependencyContainer
import com.ricky.di.appModule
import com.ricky.plugins.*
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    val dependencies = DependencyContainer.getInstance()

    install(Koin) {
        slf4jLogger()
        modules(appModule)
    }

    configureSerialization()
    configureDatabases()
    configureRouting(dependencies)
    configureValidation()
    configureStatusPages()
}