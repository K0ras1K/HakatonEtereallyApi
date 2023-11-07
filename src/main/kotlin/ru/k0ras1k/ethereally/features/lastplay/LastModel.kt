package ru.k0ras1k.ethereally.features.lastplay

import kotlinx.serialization.Serializable

@Serializable
data class LastRecieveRemoteModel(
    val login: String,
    val time: Int,
)

@Serializable
data class LastReponseRemote(
    val name: String,
)
