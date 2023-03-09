package server.udaily.pichurchyk.com.cache

import server.udaily.pichurchyk.com.feature.registration.RegistrationReceive

data class TokenCache(
    val userLogin: String,
    val token: String
)

object InMemoryCache {
    val userList: MutableList<RegistrationReceive> = mutableListOf()
    val token: MutableList<TokenCache> = mutableListOf()
}