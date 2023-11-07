package ru.k0ras1k.ethereally.models


import kotlinx.serialization.Serializable
import ru.k0ras1k.ethereally.data.EnumSection
import ru.k0ras1k.ethereally.data.EnumStatus

@Serializable
data class LoginRecieveModel(
    val email: String,
    val password: String
)

@Serializable
data class RegisterRecieveModel(
    val email: String,
    val phone_number: String,
    val first_name: String,
    val last_name: String,
    val status: String,
    val course: Int,
    val password: String,
    val section: String,
    val about: String
)

@Serializable
data class UserRespondModel(
    val email: String,
    val first_name: String,
    val last_name: String,
    val phone_number: String,
    val reg_data: Long,
    val status: String,
    val course: Int,
    val section: String,
    val about: String
)