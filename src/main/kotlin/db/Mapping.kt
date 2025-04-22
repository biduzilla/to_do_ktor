package com.ricky.db

import com.ricky.model.Priority
import com.ricky.model.Task
import com.ricky.model.User
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object UserTable : LongIdTable("user") {
    val userName = varchar("user_name", 50)
    val password = varchar("password", 60)
}

object TaskTable : LongIdTable("task") {
    val idUser = long("id_user").references(UserTable.id)
    val name = varchar("name", 100)
    val description = varchar("description", 500).nullable()
    val completed = bool("completed").default(false)
    val priority = enumerationByName("priority", 20, Priority::class)
}

class UserDAO(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<UserDAO>(UserTable)

    var userName by UserTable.userName
    var password by UserTable.password

    fun toUser(): User = User(
        idUser = id.value,
        userName = userName,
        password = password
    )
}

class TaskDAO(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<TaskDAO>(TaskTable)

    var idUser by TaskTable.idUser
    var name by TaskTable.name
    var description by TaskTable.description
    var completed by TaskTable.completed
    var priority by TaskTable.priority

    fun toTask(): Task {
        return Task(
            idTask = id.value,
            idUser = idUser,
            name = name,
            description = description,
            completed = completed,
            priority = priority
        )
    }
}

suspend fun <T> suspendTransaction(block: Transaction.() -> T): T =
    newSuspendedTransaction(Dispatchers.IO, statement = block)