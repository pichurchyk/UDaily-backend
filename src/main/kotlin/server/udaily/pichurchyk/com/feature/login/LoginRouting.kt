package server.udaily.pichurchyk.com.feature.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import server.udaily.pichurchyk.com.cache.InMemoryCache
import server.udaily.pichurchyk.com.cache.TokenCache
import server.udaily.pichurchyk.com.feature.registration.RegistrationReceive
import server.udaily.pichurchyk.com.utils.Messages
import java.util.UUID

fun Application.configureLoginRouting() {
    routing {
        post("/login") {
            val loginController = LoginController(call)
            loginController.loginUser()
        }
    }
}