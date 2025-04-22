package com.ricky.repository

import com.ricky.model.User

interface UserRepository {
    suspend fun getAll(): List<User>
    suspend fun getById(idUser: Long): User?
    suspend fun addUser(user: User):User
    suspend fun removeUser(idUser: Long)
    suspend fun getUserByName(name: String): User?
}