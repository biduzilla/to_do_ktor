package com.ricky.service

import com.ricky.exception.GenericServerError
import com.ricky.model.User
import com.ricky.repository.UserRepository
import io.ktor.http.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserService : KoinComponent {
    private val userRepository by inject<UserRepository>()
    suspend fun getAll(): List<User> {
        return userRepository.getAll()
    }

    suspend fun getById(idUser: Long): User {
        return userRepository.getById(idUser) ?: throw GenericServerError(
            httpStatus = HttpStatusCode.NotFound,
            errorMessage = "Usuário com ID $idUser não encontrado"
        )
    }

    suspend fun addUser(user: User) {

    }

    suspend fun removeUser(idUser: Long) {

    }

    suspend fun getUserByName(name: String): User? {

    }
}