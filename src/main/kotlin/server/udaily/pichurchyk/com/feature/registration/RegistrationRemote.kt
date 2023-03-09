package server.udaily.pichurchyk.com.feature.registration

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationReceive(
    val login: String,
    val password: String
)

@Serializable
data class RegistrationResponse(
    val token: String
)