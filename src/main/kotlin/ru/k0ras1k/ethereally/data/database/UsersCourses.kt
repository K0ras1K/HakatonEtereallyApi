package ru.k0ras1k.ethereally.data.database

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
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

}