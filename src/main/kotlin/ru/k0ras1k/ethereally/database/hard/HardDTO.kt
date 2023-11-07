package ru.k0ras1k.ethereally.database.hard

data class HardDTO(
    val id: Int,
    val banned: Int,
    val hwdiskid: String,
    val baseboardserialnumber: String,
    val graphiccard: String,
    val bitness: Int,
    val totalmemory: Int,
    val logicalprocessors: Int,
    val physicalProcessors: Int,
    val processormaxfreq: Int,
)
