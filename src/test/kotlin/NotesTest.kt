import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class NotesTest {

    @Before
    fun clearBeforeTest() {
        Notes.init()
    }

    @Test
    fun add() {

        val notes = Notes
        val note = Note("Note 2", "Text Note 2")
        notes.add(note)
        val result = notes.add(note)

        assertEquals(2, result)
    }

    @Test
    fun delete() {
        val notes = Notes
        val note = Note("Note 2", "Text Note 2")
        notes.add(note)
        val result = notes.delete(1)
        assertEquals(true, result)
    }

    @Test
    fun edit() {

        val notes = Notes
        val note = Note("Note 2", "Text Note 2")
        val noteEd = Note("Note Edited", "Text Note 2")
        notes.add(note)
        val result = notes.edit(1, noteEd)
        assertEquals(true, result)
    }

    @Test
    fun read() {
        val notes = Notes
        val note = Note("Note 2", "Text Note 2")
        notes.add(note)
        val result = notes.read()

        assertEquals(1, result.size)
    }

    @Test
    fun getById() {
        val notes = Notes
        val note = Note("Note Test", "Text Note 2")
        notes.add(note)
        val result = notes.getById(1)
        assertEquals("Note Test", result.title)
    }

    @Test
    fun restore() {
        val notes = Notes
        val note = Note("Note 2", "Text Note 2")
        notes.add(note)
        val result = notes.restore(1)
        assertEquals(true, result)
    }
}