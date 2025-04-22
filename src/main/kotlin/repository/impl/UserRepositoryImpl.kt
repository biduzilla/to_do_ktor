package com.ricky.repository.impl

import com.ricky.db.UserDAO
import com.ricky.db.UserTable
import com.ricky.db.suspendTransaction
import com.ricky.model.User
import com.ricky.repository.UserRepository
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

class UserRepositoryImpl : UserRepository {
    override suspend fun getAll(): List<User> {
        return suspendTransaction {
            UserDAO
                .all()
                .map { it.toUser() }
        }
    }

    override suspend fun getById(idUser: Long): User? {
        return suspendTransaction {
            UserDAO
                .find { UserTable.id eq idUser }
                .limit(1)
                .map { it.toUser() }
                .firstOrNull()
        }
    }

    override suspend fun addUser(user: User) {
        return suspendTransaction {
            UserDAO.new {
                userName = user.userName
                password = user.password
            }
        }
    }

    override suspend fun removeUser(idUser: Long) {
        suspendTransaction {
            val rowsDeleted = UserTable.deleteWhere {
                UserTable.id eq idUser
            }

            rowsDeleted == 1
        }
    }

    override suspend fun getUserByName(name: String): User? {
        return suspendTransaction {
            UserDAO
                .find { UserTable.userName eq name }
                .limit(1)
                .map { it.toUser() }
                .firstOrNull()
        }
    }
}