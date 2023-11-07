package ru.k0ras1k.ethereally.features.lastplay

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureLastRouting() {
    routing {
        authenticate("auth-jwt") {
            post("/user/lastplay/{login}/{time}") {
                val login = call.parameters["login"]?.toString()
                val time = call.parameters["time"]!!.toInt()
                val principal = call.principal<JWTPrincipal>()
                val expiresAt = principal!!.expiresAt?.time?.minus(System.currentTimeMillis())
                val username = principal!!.payload.getClaim("username").asString()
                call.respondText("Hello, $username! Token is expired at $expiresAt ms.")
                print(login)
                print(time)
                val lastController = LastController(call, login, time)
                lastController.setLastPlay()
            }
        }
    }
}
