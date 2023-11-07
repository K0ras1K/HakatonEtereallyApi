package ru.k0ras1k.ethereally.features.lastplay

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import ru.k0ras1k.ethereally.database.last_play.Last

class LastController(val call: ApplicationCall, val login: String?, val time: Int?) {

    suspend fun setLastPlay() {
        Last.updateLast(login!!, time!!)
        call.respond(HttpStatusCode.OK)
    }
}
