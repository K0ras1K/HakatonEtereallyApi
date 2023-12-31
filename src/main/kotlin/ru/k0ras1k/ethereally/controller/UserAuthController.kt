package ru.k0ras1k.ethereally.controller

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.coroutines.runBlocking
import ru.k0ras1k.ethereally.data.EnumSection
import ru.k0ras1k.ethereally.data.EnumStatus
import ru.k0ras1k.ethereally.data.UserData
import ru.k0ras1k.ethereally.data.database.Users
import ru.k0ras1k.ethereally.models.UpdateUserRecieveModel
import ru.k0ras1k.ethereally.models.UserRespondModel
import ru.k0ras1k.ethereally.utils.Logger
import ru.k0ras1k.ethereally.utils.controller.AbstractController

class UserAuthController(call: ApplicationCall) : AbstractController(call) {

    suspend fun load() {
        runBlocking {
            try {
                val user_data: UserData = Users.fetchUser(username)!!
                val user_respond: UserRespondModel = UserRespondModel(
                    email = user_data.email,
                    first_name = user_data.first_name,
                    last_name = user_data.last_name,
                    phone_number = user_data.phone_number,
                    reg_data = user_data.reg_data,
                    status = user_data.status.toString(),
                    course = user_data.course!!,
                    section = user_data.section.toString(),
                    about = user_data.about!!
                )
                call.respond(HttpStatusCode.OK, user_respond)
            }
            catch (e: Exception) {
                e.printStackTrace()
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }

    suspend fun update() {
        // Получаем десериализованный дата класс
        val recieve = call.receive<UpdateUserRecieveModel>()
        // Пытаемся получить юзера
        Users.update(
            UserData(
                email = username,
                first_name = "123",
                last_name = "123",
                phone_number = "123",
                reg_data = System.currentTimeMillis(),
                password = "123",
                status = EnumStatus.valueOf(recieve.status),
                course = recieve.course,
                section = EnumSection.valueOf(recieve.section),
                about = recieve.about
            )
        )


    }

}