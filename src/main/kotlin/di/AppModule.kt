package com.ricky.di

import com.ricky.repository.UserRepository
import com.ricky.repository.impl.TaskRepositoryImpl
import com.ricky.repository.impl.UserRepositoryImpl
import com.ricky.service.impl.UserServiceImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    factory { UserRepositoryImpl() } bind UserRepository::class
    factory { TaskRepositoryImpl() } bind TaskRepositoryImpl::class

    single { UserServiceImpl(get()) }
}