package ru.k0ras1k.ethereally.data.database

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import ru.k0ras1k.ethereally.data.EnumSection
import ru.k0ras1k.ethereally.data.EnumStatus
import ru.k0ras1k.ethereally.data.UserData
import ru.k0ras1k.ethereally.utils.Logger

object Users : Table("users") {

    private val email = Users.varchar("email", 100)
    private val first_name = Users.varchar("first_name", 60)
    private val last_name = Users.varchar("last_name", 60)
    private val password = Users.varchar("password", 100)
    private val reg_time = Users.long("reg_time")
    private val phone_number = Users.varchar("phone_number", 10)
    private val status = Users.varchar("status", 50)
    private val course = Users.integer("course")
    private val section = Users.varchar("section", 50)
    private val about = Users.varchar("about", 500)

    fun insert(user_data: UserData) {
        try {
            transaction {
                SchemaUtils.create(Users)
                insert {
                    it[email] = user_data.email
                    it[first_name] = user_data.first_name
                    it[last_name] = user_data.last_name
                    it[password] = user_data.password
                    it[reg_time] = user_data.reg_data
                    it[phone_number] = user_data.phone_number
                    it[status] = user_data.status.toString()
                    it[course] = user_data.course
                    it[section] = user_data.section.toString()
                    it[about] = user_data.about
                }
            }
        }
        catch (e: Exception) {
            Logger.error(e.stackTrace.toString())
        }
    }

    fun fetchUser(email: String): UserData? {
        return try {
            transaction {
                SchemaUtils.create(Users)
                val user_model = Users.select { Users.email.eq(email) }.single()
                UserData(
                    email = user_model[Users.email],
                    first_name = user_model[first_name],
                    last_name = user_model[last_name],
                    password = user_model[password],
                    reg_data = user_model[reg_time],
                    phone_number = user_model[phone_number],
                    status = EnumStatus.valueOf(user_model[status]),
                    course = user_model[course],
                    section = EnumSection.valueOf(user_model[section]),
                    about = user_model[about]
                )
            }
        }
        catch (e: Exception) {
            Logger.error(e.stackTrace.toString())
            null
        }
    }

}