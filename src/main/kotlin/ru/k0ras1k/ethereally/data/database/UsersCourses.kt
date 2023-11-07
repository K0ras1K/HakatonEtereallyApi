package ru.k0ras1k.ethereally.data.database

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import ru.k0ras1k.ethereally.data.EnumCourse
import ru.k0ras1k.ethereally.data.UserCourseData
import ru.k0ras1k.ethereally.utils.Logger

object UsersCourses : Table("users_courses") {

    private val email = UsersCourses.varchar("email", 100)
    private val course = UsersCourses.varchar("course", 50)
    private val complete = UsersCourses.integer("complete")

    fun insertCourse(user_course_data: UserCourseData) {
        try {
            transaction {
                SchemaUtils.create(UsersCourses)
                insert {
                    it[email] = user_course_data.email
                    it[course] = user_course_data.course.toString()
                    it[complete] = user_course_data.complete
                }
            }
        }
        catch (e: Exception) {
            Logger.error(e.stackTrace.toString())
        }
    }

    fun updateCourse(user_course_data: UserCourseData) {
        try {
            transaction {
                SchemaUtils.create(UsersCourses)
                UsersCourses.update( { email.eq(user_course_data.email) } ) {
                    it[email] = user_course_data.email
                    it[course] = user_course_data.course.toString()
                    it[complete] = user_course_data.complete
                }
            }
        }
        catch (e: Exception) {
            Logger.error(e.stackTrace.toString())
        }
    }

    fun getCourses(email: String): List<UserCourseData>? {
        return try {
            transaction {
                SchemaUtils.create(UsersCourses)
                val user_courses_data: MutableList<UserCourseData> = mutableListOf()
                val user_courses_model = UsersCourses.select { UsersCourses.email.eq(email) }.forEach {
                    it ->
                    user_courses_data.add(UserCourseData(
                        email = email,
                        course = EnumCourse.valueOf(it[course]),
                        complete = it[complete]
                    ))
                }
                user_courses_data
            }
        }
        catch (e: Exception) {
            Logger.error(e.stackTrace.toString())
            null
        }
    }
}