package server.udaily.pichurchyk.com.utils

fun String.checkIsValidPassword(): Boolean = this.length >= 5
fun String.checkIsValidLogin(): Boolean = this.length >= 3