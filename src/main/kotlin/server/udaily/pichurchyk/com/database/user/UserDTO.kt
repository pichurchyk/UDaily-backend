package server.udaily.pichurchyk.com.database.user

data class UserDTO(
    val login: String,
    val password: String
) {
    fun isPasswordEquals(password: String): Boolean = password == this.password
}