package com.ricky.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val idUser:Long? = null,
    val userName:String,
    val password:String,
)
