package ru.k0ras1k.ethereally.database.last_play

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

object Last : Table("last_play") {

    private val name = Last.varchar("login", 20)
    private val time = Last.integer("last_play")

    fun updateLast(login: String, time: Int) {
        transaction {
            SchemaUtils.create(Last)
            Last.update({ name.eq(login) }) {
                it[Last.time] = time
            }
        }
    }

    fun fetchLast(login: String): LastDTO? {
        return try {
            transaction {
                SchemaUtils.create(Last)
                val groupModel = Last.select { name.eq(login) }.single()
                LastDTO(
                    name = groupModel[name],
                    time = groupModel[time],
                )
            }
        } catch (e: Exception) {
            null
        }
    }
}
