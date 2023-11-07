package ru.k0ras1k.ethereally.features.hard

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

class HardController(val call: ApplicationCall, val login: String?) {
    suspend fun hardBan() {
    }

    suspend fun unHardBan() {
//            Hard.unHardBan(userDto.id.toInt())
        call.respond(HttpStatusCode.OK)
    }

    suspend fun isHardBanned() {
//        val userDto = Users.fetchUser(login!!)
//        val hardDto = Hard.fetchHard(userDto!!.id.toInt())
//        println(hardDto!!.banned)
//        if (hardDto!!.banned == 1) {
//            call.respond(HttpStatusCode.OK, "true")
//        }
//        else {
//            call.respond(HttpStatusCode.OK, "false")
//        }
    }

    suspend fun getHardInfo() {
//        val userDto = Users.fetchUser(login!!)
//        val hardDto = Hard.fetchHard(userDto!!.id.toInt())!!
//        println(hardDto!!.banned)
//        call.respond(HttpStatusCode.OK, "${hardDto.banned}/${hardDto.graphiccard}/${hardDto.hwdiskid}/${hardDto.bitness}/${hardDto.baseboardserialnumber}/${hardDto.logicalprocessors}/${hardDto.physicalProcessors}/${hardDto.processormaxfreq}/${hardDto.totalmemory}")
    }

    suspend fun getHardDupe() {
//        val users = Users.fetchUser(userDto!!.id)
        var string = ""
//        val iterator = users!!.iterator()
//        for (item in iterator) {
//            string += item
//            string += "/"
//        }
        call.respond(HttpStatusCode.OK, string)
    }
}
