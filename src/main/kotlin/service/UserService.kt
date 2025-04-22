package com.ricky.service

import com.ricky.exception.GenericServerError
import com.ricky.model.User
import com.ricky.repository.UserRepository
import io.ktor.http.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserService(private val userRepository:UserRepository) {
//    private val userRepository by inject<UserRepository>()
    suspend fun getAll(): List<User> {
        return userRepository.getAll()
    }

    suspend fun getById(idUser: Long): User {
        return userRepository.getById(idUser) ?: throw GenericServerError(
            httpStatusCode = HttpStatusCode.NotFound.value,
            errorMessage = "Usuário com ID $idUser não encontrado",
            errorCode = HttpStatusCode.NotFound.description
        )
    }

    suspend fun addUser(user: User):User {
        return userRepository.addUser(user)
    }

    suspend fun removeUser(idUser: Long) {
        userRepository.removeUser(idUser)
    }

    suspend fun getUserByName(name: String): User {
        return userRepository.getUserByName(name) ?: throw GenericServerError(
            httpStatusCode = HttpStatusCode.NotFound.value,
            errorMessage = "Usuário com nome $name não encontrado",
            errorCode = HttpStatusCode.NotFound.description
        )
    }
}