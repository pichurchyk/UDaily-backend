package server.udaily.pichurchyk.com.database.note

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import server.udaily.pichurchyk.com.feature.note.NoteResponseRemote

object Notes : Table("notes") {
    private val id = Notes.integer("id")
    private val userLogin = Notes.varchar("userLogin", 25)
    private val text = Notes.varchar("text", 150)
    private val date = Notes.varchar("date", 25)

    fun insert(noteDTO: NoteDTO) {
        transaction {
            Notes.insert {
                it[userLogin] = noteDTO.userLogin
                it[text] = noteDTO.text
                it[date] = noteDTO.date
            }
        }
    }

    fun fetch(): List<NoteResponseRemote> {
        return transaction {
            Notes.selectAll().map {
                NoteResponseRemote(
                    id = it[Notes.id],
                    text = it[Notes.text],
                    date = it[Notes.date]
                )
            }
        }
    }
}