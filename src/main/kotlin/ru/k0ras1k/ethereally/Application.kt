package ru.k0ras1k.ethereally

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.websocket.*
import org.jetbrains.exposed.sql.Database
import ru.k0ras1k.ethereally.features.hard.configureHardRouting
import ru.k0ras1k.ethereally.features.lastplay.configureLastRouting
import ru.k0ras1k.ethereally.plugins.configureJWT
import ru.k0ras1k.ethereally.plugins.configureRouting
import ru.k0ras1k.ethereally.plugins.conigureSerialization
import ru.k0ras1k.ethereally.routing.configurePublicRouting
import java.time.Duration

fun main() {
//    val site_db = Database.connect("jdbc:mariadb://86.110.212.152:3306/testsite?enabledTLSProtocols=TLSv1.2&serverTimezone=UTC", driver = "org.mariadb.jdbc.Driver", user = "neferpito", password = "Shah9Sah.")
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module).start(wait = true)
}

fun Application.module() {
    // connect to database using Hikari Connection Pool
    val db = Database.connect(
        ru.k0ras1k.ethereally.DatabaseFactory.createHikariDataSource(
            "jdbc:mariadb://mariadb:3306/ethereally?enabledTLSProtocols=TLSv1.2&serverTimezone=UTC",
            "org.mariadb.jdbc.Driver",
            "root",
        ),
    )

    // "86.110.212.152"
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    configureRouting()
    conigureSerialization()
    configureLastRouting()
    configureHardRouting()
    configureJWT()
    configurePublicRouting()

    Initialization.initialize()
}
