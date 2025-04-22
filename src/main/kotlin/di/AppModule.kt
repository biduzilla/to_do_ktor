package com.ricky.di

import com.ricky.repository.TaskRepository
import com.ricky.repository.UserRepository
import com.ricky.repository.impl.TaskRepositoryImpl
import com.ricky.repository.impl.UserRepositoryImpl
import com.ricky.service.UserService
import org.koin.dsl.module

val appModule = module {
    factory<UserRepository> { UserRepositoryImpl() }
    factory<TaskRepository> { TaskRepositoryImpl() }

    single { UserService(get()) }
}