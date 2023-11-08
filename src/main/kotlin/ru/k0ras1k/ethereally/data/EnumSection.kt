package ru.k0ras1k.ethereally.data

enum class EnumSection(val COURSES: List<EnumCourse>) {

    ECONOMY(listOf(EnumCourse.INNOVATION, EnumCourse.BUSINESS_INFORMATICS)),
    PHYSICS(listOf(EnumCourse.OPTO_INFORMATICS, EnumCourse.WIRELESS_TECHNOLODY)),
    ENGINEERING(listOf(EnumCourse.ROBOTO_TECH, EnumCourse.BIO_ENGINEERING)),
    IT(listOf(EnumCourse.AI, EnumCourse.INFO_SECURITY, EnumCourse.BACK_END, EnumCourse.FRONT_END, EnumCourse.GAME_DEV, EnumCourse.BUSINESS_INFORMATICS)),
    LIFE_SCIENCE(listOf(EnumCourse.BIO_ENGINEERING, EnumCourse.ECO_TECHNOLOGY, EnumCourse.INFO_CHEMISTRY)),
    NONE(listOf())

}