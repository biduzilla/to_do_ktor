package com.ricky.service.impl

import com.ricky.exception.GenericServerError
import com.ricky.model.User
import com.ricky.repository.UserRepository
import com.ricky.service.UserService
import io.ktor.http.*

class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    //    private val userRepository by inject<UserRepository>()
    override suspend fun getAll(): List<User> {
        return userRepository.getAll()
    }

    override suspend fun getById(idUser: Long): User {
        return userRepository.getById(idUser) ?: throw GenericServerError(
            statusCode = HttpStatusCode.NotFound.value,
            errorMessage = "Usuário com ID $idUser não encontrado",
            httpStatus = HttpStatusCode.NotFound.description
        )
    }

    override suspend fun addUser(user: User): User {
        return userRepository.addUser(user)
    }

    override suspend fun removeUser(idUser: Long) {
        userRepository.removeUser(idUser)
    }

    override suspend fun getUserByName(name: String): User {
        return userRepository.getUserByName(name) ?: throw GenericServerError(
            statusCode = HttpStatusCode.NotFound.value,
            errorMessage = "Usuário com nome $name não encontrado",
            httpStatus = HttpStatusCode.NotFound.description
        )
    }
}