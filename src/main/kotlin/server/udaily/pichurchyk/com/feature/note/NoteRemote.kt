package server.udaily.pichurchyk.com.feature.note

import kotlinx.serialization.Serializable

@Serializable
data class NoteReceiveRemote(
    val login: String,
    val text: String,
    val date: String
)

@Serializable
data class NoteResponseRemote(
    val id: Int,
    val text: String,
    val date: String
)