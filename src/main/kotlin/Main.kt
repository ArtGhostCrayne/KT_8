data class Note(
    val id: Int,
    val title: String,
    val text: String,
    val privacy: Int = 0,
    val commentPrivacy: Int = 0,
    val date: Long = System.currentTimeMillis() / 1000,
    val delete: Boolean = false
)

data class Comment(
    val id: Int,
    val noteID: Int,
    val message: String,
    val date: Long = System.currentTimeMillis() / 1000,
    val delete: Boolean = false
)

object Notes {
    private var noteID = 1;
    private var commentID = 1;
    private var notes: MutableMap<Int, Note> = mutableMapOf()
    private var comments: MutableMap<Int, Comment> = mutableMapOf()

    fun add(title: String, text: String, privacy: Int = 0, commentPrivacy: Int = 0): Int {
        notes[noteID] = Note(noteID, title, text, privacy, commentPrivacy)
        noteID += 1
        return noteID
    }

    fun init(){
        notes.clear()
        comments.clear()
        noteID = 1
        commentID = 1
    }


    fun createComment(noteID: Int, message: String): Int {
        comments[commentID] = Comment(commentID, noteID, message)
        commentID += 1
        return commentID
    }

    fun delete(noteID: Int): Boolean {
        if (notes.containsKey(noteID)) {
            if (!notes.getValue(noteID).delete) {
                notes[noteID] = notes.getValue(noteID).copy(delete = true)
                return true
            }
        }
        return false
    }

    fun deleteComment(commentID: Int): Boolean {
        return editComment(commentID, "", true)
    }

    fun edit(noteID: Int, title: String, text: String, privacy: Int = 0, commentPrivacy: Int = 0): Boolean {
        if (notes.containsKey(noteID)) {
            if (!notes.getValue(noteID).delete) {
                notes[noteID] =
                    notes[noteID]!!.copy(title = title, text = text, privacy = privacy, commentPrivacy = commentPrivacy)
                return true
            }
        }
        return false
    }

    fun editComment(commentID: Int, message: String, delete: Boolean = false): Boolean {
        if (comments.containsKey(commentID)) {

            val mes = if (message == "") {
                comments.getValue(commentID).message
            } else {
                message
            }
            comments[commentID] = comments[commentID]!!.copy(message = mes, delete = delete)
            return true

        }
        return false
    }

    fun getComments(noteId: Int, sort: Boolean = false, count: Int = 100): MutableList<Comment> {
        var cnt = 0
        var arr: MutableList<Comment> = mutableListOf()
        for ((key, value) in comments.entries) {
            if (value.noteID == noteId && cnt < count) {
                arr += value
                cnt += 1
            }
        }
        if (sort) {
            arr.sortBy { it.date }
        } else {
            arr.sortByDescending { it.date }
        }

        return arr
    }

    fun restoreComment(commentID: Int): Boolean {
        return editComment(commentID, "", false)
    }

    fun getById(noteId: Int): Note? {
        return notes[noteId]
    }
}


fun main(args: Array<String>) {

    val note = Notes

//    note.add("Note 1", "Text Note 1")
//    note.add("Note 2", "Text Note 2")
    println(note.getById(1))

    note.createComment(1, "2")
    Thread.sleep(1000)
    note.createComment(1, "1")
    Thread.sleep(1000)
    note.createComment(1, "3")
//    println(note.notes)

//    println(note.comments)

    //   println(note.editComment(3,"Edited"))

    println(note.getComments(1, false, 3))
    note.deleteComment(1)
    println(note.getComments(1, false, 3))
    note.restoreComment(1)
    println(note.getComments(1, false, 3))

//    println(note.comments)

//    println(note.delete(3))
//    println(note.notes)

}