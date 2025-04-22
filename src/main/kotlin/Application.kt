package com.ricky

import com.ricky.di.appModule
import com.ricky.plugins.configureDatabases
import com.ricky.plugins.configureRouting
import com.ricky.plugins.configureSerialization
import com.ricky.repository.impl.TaskRepositoryImpl
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    install(Koin){
        slf4jLogger()
        modules(appModule)
    }

    configureSerialization()
    configureDatabases()
    configureRouting()
}
