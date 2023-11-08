package ru.k0ras1k.ethereally.controller

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.mindrot.jbcrypt.BCrypt
import ru.k0ras1k.ethereally.data.EnumSection
import ru.k0ras1k.ethereally.data.EnumStatus
import ru.k0ras1k.ethereally.data.UserData
import ru.k0ras1k.ethereally.data.database.Users
import ru.k0ras1k.ethereally.models.LoginRecieveModel
import ru.k0ras1k.ethereally.models.RegisterRecieveModel
import ru.k0ras1k.ethereally.models.UpdateUserRecieveModel
import ru.k0ras1k.ethereally.models.UserLoginRespondModel
import ru.k0ras1k.ethereally.utils.Validator
import java.util.*

class UserController(val call: ApplicationCall) {

    suspend fun login() {
        val recieve = call.receive<LoginRecieveModel>()
        val user_data = Users.fetchUser(recieve.email)

        if (user_data == null) {
            call.respond(HttpStatusCode.BadRequest, "User not found")
        } else {
            if (BCrypt.checkpw(recieve.password, user_data.password)) {
                val secret = ";zdofijgn9283y4;kljn"
//                val issuer = call.application.environment.config.property("jwt.issuer").getString()
//                val audience = call.application.environment.config.property("jwt.audience").getString()
//                val myRealm = call.application.environment.config.property("jwt.realm").getString()

                val token = JWT.create()
                    .withClaim("email", user_data.email)
                    .withClaim("password", user_data.password)
                    .withExpiresAt(Date(System.currentTimeMillis() + 5 * 60 * 60 * 60 * 24 * 60))
                    .sign(Algorithm.HMAC256(secret))
                call.respond(HttpStatusCode.OK, UserLoginRespondModel(token))
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }

    suspend fun registerUser() {
        // Получаем десериализованный дата класс
        val recieve = call.receive<RegisterRecieveModel>()
        // Пытаемся получить юзера
        val user_data = Users.fetchUser(recieve.email)

        // Проверяем ввалидность емаила
        if (!Validator.isValidEmail(recieve.email)) {
            call.respond(HttpStatusCode.BadRequest, "Email is not valid")
            return
        }

        if (user_data != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
            return
        }
        else {
            try {
                // Хэшируем пароль
                val hash = BCrypt.hashpw(recieve.password, BCrypt.gensalt())

                val user_data_1: UserData = UserData(
                    email = recieve.email,
                    first_name = recieve.first_name,
                    last_name = recieve.last_name,
                    phone_number = recieve.phone_number,
                    reg_data = System.currentTimeMillis(),
                    password = hash,
                    status = null,
                    course = null,
                    section = null,
                    about = null
                )

                Users.insert(user_data_1)

                val secret = ";zdofijgn9283y4;kljn"
                val token = JWT.create()
                    .withClaim("email", user_data_1.email)
                    .withClaim("password", user_data_1.password)
                    .withExpiresAt(Date(System.currentTimeMillis() + 5 * 60 * 60 * 60 * 24 * 60))
                    .sign(Algorithm.HMAC256(secret))
                call.respond(HttpStatusCode.OK, UserLoginRespondModel(token))
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
            }
        }
    }

}