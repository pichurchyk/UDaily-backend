package server.udaily.pichurchyk.com.feature.registration

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import server.udaily.pichurchyk.com.database.token.TokenDTO
import server.udaily.pichurchyk.com.database.token.Tokens
import server.udaily.pichurchyk.com.database.user.UserDTO
import server.udaily.pichurchyk.com.database.user.Users
import server.udaily.pichurchyk.com.utils.Messages
import server.udaily.pichurchyk.com.utils.checkIsValidLogin
import server.udaily.pichurchyk.com.utils.checkIsValidPassword
import java.util.UUID

class RegisterController(private val call: ApplicationCall) {

    suspend fun registerNewUser() {
        val receive = call.receive(RegistrationReceive::class)
        if (!receive.login.checkIsValidLogin()) {
            call.respond(HttpStatusCode.BadRequest, Messages.LOGIN_IS_NOT_VALID)
        }

        if (!receive.password.checkIsValidPassword()) {
            call.respond(HttpStatusCode.BadRequest, Messages.PASSWORD_IS_NOT_VALID)
        }

        val isUserExist = Users.fetchUser(receive.login) != null

        if (isUserExist) {
            call.respond(HttpStatusCode.Conflict, Messages.USER_EXISTS)
        } else {
            val token = UUID.randomUUID().toString()

            try {
                Users.insert(
                    UserDTO(
                        login = receive.login,
                        password = receive.password
                    )
                )
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, Messages.USER_EXISTS)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "${Messages.CANT_CREATE_USER} (${e.localizedMessage})")
            }

            Tokens.insert(
                TokenDTO(
                    id = UUID.randomUUID().toString(),
                    login = receive.login,
                    token = token
                )
            )

            call.respond(RegistrationResponse(token = token))
        }
    }
}