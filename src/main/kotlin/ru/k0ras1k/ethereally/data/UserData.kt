package ru.k0ras1k.ethereally.data

data class UserData(

    val email: String,
    val first_name: String,
    val last_name: String,
    val phone_number: String,
    val reg_data: Long,
    val password: String,
    val status: EnumStatus,
    val course: Int,
    val section: EnumSection,
    val about: String

)
