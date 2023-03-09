package server.udaily.pichurchyk.com.feature.note

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureNoteRouting() {
    routing {
        post("/notes/add") {
            val noteController = NoteController(call)
            noteController.addNote()
        }

        get("/notes/get") {
            val noteController = NoteController(call)
            noteController.fetchNotes()
        }
    }
}