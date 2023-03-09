package server.udaily.pichurchyk.com.feature.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import server.udaily.pichurchyk.com.database.token.TokenDTO
import server.udaily.pichurchyk.com.database.token.Tokens
import server.udaily.pichurchyk.com.database.user.Users
import server.udaily.pichurchyk.com.utils.Messages
import java.util.UUID

class LoginController(private val call: ApplicationCall) {

    suspend fun loginUser() {
        val receive = call.receive(LoginReceiveRemote::class)
        val existingUser = Users.fetchUser(receive.login)

        existingUser?.let { dbUser ->
            if (dbUser.isPasswordEquals(receive.password)) {
                val token = UUID.randomUUID().toString()
                val id = UUID.randomUUID().toString()
                Tokens.insert(
                    TokenDTO(
                        id = id,
                        login = receive.login,
                        token = token
                    )
                )
                call.respond(LoginResponseRemote(token = token))
            } else {
                call.respond(HttpStatusCode.BadRequest, Messages.INCORRECT_LOGIN_DATA)
            }
        } ?: call.respond(HttpStatusCode.BadRequest, Messages.USER_NOT_FOUND)
    }
}