package server.udaily.pichurchyk.com.feature.registration

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRegistrationRouting() {
    routing {
        post("/register") {
            val registerController = RegisterController(call)
            registerController.registerNewUser()
        }
    }
}