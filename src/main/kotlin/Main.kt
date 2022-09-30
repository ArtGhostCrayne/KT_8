
interface CrudService<E> {
    fun add(entity: E): Long
    fun delete(id: Long): Boolean
    fun edit(id: Long, entity: E): Boolean
    fun read(): List<E>
    fun getById(id: Long): E
    fun restore(id: Long): Boolean
}


data class Note(
    val title: String,
    val text: String,
    val privacy: Int = 0,
    val commentPrivacy: Int = 0,
    val date: Long = System.currentTimeMillis() / 1000,
    val delete: Boolean = false
)

data class Comment(
    val noteID: Int,
    val message: String,
    val date: Long = System.currentTimeMillis() / 1000,
    val delete: Boolean = false
)

object Comments: CrudService<Comment> {
    private var comments: MutableMap<Long, Comment> = mutableMapOf()


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

    override fun add(entity: Comment): Long {
        val id: Long = (comments.size+1).toLong()
        comments[id] = entity
        return id
    }

    override fun delete(id: Long): Boolean {
        if (comments.containsKey(id)) {
            val comment = comments.getValue(id).copy(delete = true)
            return edit(id, comment)
        }
        return false
    }

    override fun edit(id: Long, entity: Comment): Boolean {
        if (comments.containsKey(id)) {
            if (!comments.getValue(id).delete) {
                comments[id] = entity
                return true
            }
        }
        return false
    }

    override fun read(): List<Comment> {
        var com: List<Comment> = emptyList()
        for ((key, value) in comments.entries){
           com += value
        }
        return com
    }

    override fun getById(id: Long): Comment {
        return comments[id]!!
    }

    override fun restore(id: Long): Boolean {
        if (comments.containsKey(id)) {
            val comment = comments.getValue(id).copy(delete = false)
            return edit(id, comment)
        }
        return false
    }

}

object Notes: CrudService<Note> {
    private var notes: MutableMap<Long, Note> = mutableMapOf()

    fun init(){
        notes.clear()
    }

    override fun add(entity: Note): Long {
        val id: Long = (notes.size+1).toLong()
        notes[id] = entity
        return id
    }

    override fun delete(id: Long): Boolean {
        if (notes.containsKey(id)) {
            val note = notes.getValue(id).copy(delete = true)
            return edit(id, note)
        }
        return false
    }

    override fun edit(ID: Long, entity: Note): Boolean {
        if (notes.containsKey(ID)) {
            if (!notes.getValue(ID).delete) {
                notes[ID] = entity
                return true
            }
        }
        return false
    }

    override fun read(): List<Note> {
        var note: List<Note> = emptyList()
        for ((key, value) in notes.entries){
            note += value
        }
        return note
    }

    override fun getById(id: Long): Note {
        return notes[id]!!
    }

    override fun restore(id: Long): Boolean {
        val note = notes.getValue(id).copy(delete = false)
        return edit(id, note)
    }

}


fun main(args: Array<String>) {

    

}