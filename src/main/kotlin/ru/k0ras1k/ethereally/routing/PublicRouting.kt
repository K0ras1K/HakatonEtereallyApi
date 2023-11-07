package ru.k0ras1k.ethereally.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.k0ras1k.ethereally.controller.UserAuthController
import ru.k0ras1k.ethereally.controller.UserController


fun Application.configurePublicRouting() {
    val ROUTING_PREFIX: String = "/public"

    routing {
        // UNAUTHENTICATED
        get("/v") {
            call.respond(HttpStatusCode.OK, "Ethereally-api version 0.1")
        }

        post("$ROUTING_PREFIX/users/register") {
            UserController(call).registerUser()
        }

        post("$ROUTING_PREFIX/users/login") {
            UserController(call).login()
        }

        // AUTHENTICATED
        authenticate("auth-jwt") {
            get("$ROUTING_PREFIX/users/get") {
                UserAuthController(call).load()
            }

            post("$ROUTING_PREFIX/course/tryupdate") {
                //TODO Попытка обновления активности на курсе. Требует подтверждение учителя в ТГ
            }

            post("$ROUTING_PREFIX/course/join") {
                //TODO Инсертить курс. Вступление на него
            }

            get("$ROUTING_PREFIX/course/get") {
                //TODO Отправлять все курсы юзера с прогрессом
            }
        }
    }
}
