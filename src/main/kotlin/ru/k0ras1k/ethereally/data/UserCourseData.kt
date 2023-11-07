package ru.k0ras1k.ethereally.data

data class UserCourseData (
    val email: String,
    val course: EnumCourse,
    val complete: Int
)