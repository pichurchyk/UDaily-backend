package server.udaily.pichurchyk.com

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import org.jetbrains.exposed.sql.Database
import server.udaily.pichurchyk.com.feature.login.configureLoginRouting
import server.udaily.pichurchyk.com.feature.note.configureNoteRouting
import server.udaily.pichurchyk.com.feature.registration.configureRegistrationRouting
import server.udaily.pichurchyk.com.plugins.*

fun main() {
    Database.connect(
        "jdbc:postgresql://localhost:5432/UDaily",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "Vbrc123zx"
    )

    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureHTTP()
    configureSockets()
    configureSerialization()
    configureSecurity()

    configureLoginRouting()
    configureRegistrationRouting()
    configureNoteRouting()
}
