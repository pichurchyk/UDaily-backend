package server.udaily.pichurchyk.com.feature.note

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import server.udaily.pichurchyk.com.database.note.NoteDTO
import server.udaily.pichurchyk.com.database.note.Notes

class NoteController(private val call: ApplicationCall) {

    suspend fun addNote() {
        val receive = call.receive(NoteReceiveRemote::class)

        try {
            Notes.insert(
                NoteDTO(
                    userLogin = receive.login,
                    text = receive.text,
                    date = receive.date
                )
            )

            call.respond(HttpStatusCode.OK)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, e.localizedMessage)
        }
    }

    suspend fun fetchNotes() {
        try {
            call.respond(Notes.fetch())
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, e.localizedMessage)
        }
    }
}