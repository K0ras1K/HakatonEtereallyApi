package ru.k0ras1k.ethereally.features.hard

import kotlinx.serialization.Serializable

@Serializable
data class HardRecieveRemoteModel(
    val login: String,
    val ban: Int,
)
