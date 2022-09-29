import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class NotesTest {

    @Before
    fun clearBeforeTest() {
        Notes.init()
        println("Before")
    }

    @Test
    fun add() {
        val note = Notes
        val result = note.add("Note 1", "Text Note 1")
        assertEquals(2, result)
    }

    @Test
    fun createComment() {
        val note = Notes
        val result = note.createComment(1, "Test")
        assertEquals(2, result)
    }

    @Test
    fun delete() {
        val note = Notes
        note.add("Note 1", "Text Note 1")
        val result = note.delete(1)
        assertEquals(true, result)
    }
//
    @Test
    fun deleteComment() {
        val note = Notes
        note.add("Note 1", "Text Note 1")
        note.createComment(1,"Test")
        val result = note.deleteComment(1)
        assertEquals(true, result)
    }

    @Test
    fun edit() {
        val note = Notes
        note.add("Note 1", "Text Note 1")
        val result = note.edit(1,"Test", "test")
        assertEquals(true, result)
    }

    @Test
    fun editComment() {
        val note = Notes
        note.add("Note 1", "Text Note 1")
        note.createComment(1,"Test")
        val result = note.editComment(1,"Test",)
        assertEquals(true, result)
    }

    @Test
    fun getComments() {
        val note = Notes
        note.add("Note 1", "Text Note 1")
        note.createComment(1,"Test")
        note.createComment(2,"Test2")
        note.createComment(3,"Test3")
        val result = note.getComments(1)

        assertEquals(1, result.size)

    }

    @Test
    fun restoreComment() {
        val note = Notes
        note.add("Note 1", "Text Note 1")
        note.createComment(1,"Test")
        note.deleteComment(1)
        val result = note.restoreComment(1)
        assertEquals(true, result)

    }

    @Test
    fun getById() {
        val note = Notes
        note.add("Note 1", "Text Note 1")
        val result = note.getById(1)
        assertTrue(result!=null)
    }
}