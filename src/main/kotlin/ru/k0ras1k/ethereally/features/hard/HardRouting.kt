package ru.k0ras1k.ethereally.features.hard

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureHardRouting() {
    routing {
        post("/user/hard/{login}") {
            val login = call.parameters["login"]?.toString()
            if (call.request.queryParameters["secret"] == "lndfgOJndfogljkn235nol97gfsdbnjkk2088h-12937gb") {
                val hardController = HardController(call, login)
                val method: Int = call.request.queryParameters["ban"]!!.toInt()
                if (method == 1) {
                    hardController.hardBan()
                } else {
                    hardController.unHardBan()
                }
            }
        }
        get("/user/hashard/{login}") {
            val login = call.parameters["login"]?.toString()
            if (call.request.queryParameters["secret"] == "lndfgOJndfogljkn235nol97gfsdbnjkk2088h-12937gb") {
                val hardController = HardController(call, login)
                hardController.isHardBanned()
            }
        }
        get("/user/gethard/{login}") {
            val login = call.parameters["login"]?.toString()
            if (call.request.queryParameters["secret"] == "lndfgOJndfogljkn235nol97gfsdbnjkk2088h-12937gb") {
                val hardController = HardController(call, login)
                hardController.getHardInfo()
            }
        }
        get("/user/harddupe/{login}") {
            val login = call.parameters["login"]?.toString()
            if (call.request.queryParameters["secret"] == "lndfgOJndfogljkn235nol97gfsdbnjkk2088h-12937gb") {
                val hardController = HardController(call, login)
                hardController.getHardDupe()
            }
        }
    }
}
