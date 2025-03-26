fun main() {
    val like = Likes(0, false, true)
    val post = Post(ownerId = 1, fromId = 1,text="Hello World", can_edit = true, date = 1742682123, likes=like)

    WallService.addPost(post)       // публикуем 1-й пост

    val post2 = Post(ownerId = 1, fromId = 1,text="Hello World Again", can_edit = true, date = 1742682123, likes=like)

    WallService.addPost(post2)      // публикуем 2-й пост

    WallService.addLike(1)      // даем лайк 1-му посту

    println(WallService.getPostInfo(1)) // посмотрим, как записались лайки к первому посту...

    // теперь обновим 1-й пост и посмотрим, что получилось
    WallService.update(Post(1,3,3,"This is new text", true, 1742682999, Likes(999, false,true)))
    println(WallService.getPostInfo(1))

}

data class Post(
    val id: Int = 0,            // Идентификатор записи.
    val ownerId: Int,           // Идентификатор владельца стены, на которой размещена запись.
    val fromId: Int,            // Идентификатор автора записи (от чьего имени опубликована запись).
    val text: String,           // Текст записи.
    val can_edit: Boolean,      // Информация о том, может ли текущий пользователь редактировать запись (1 — может, 0 — не может).
    val date: Int,              // Время публикации записи в формате unixtime.
    val likes: Likes
)

class Likes(
    val count: Int,              // число пользователей, которым понравилась запись;
    val userLikes: Boolean,      // наличие отметки «Мне нравится» от текущего пользователя
    val canLike: Boolean,        // информация о том, может ли текущий пользователь поставить отметку «Мне нравится»
)

object WallService {

    private var posts = emptyArray<Post>()

    fun addPost(post:Post): Post {
        posts += post.copy(id=getMaxPostId()+1)
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, postedPost) in posts.withIndex()) {
            if (postedPost.id == post.id) {
                posts[index] = post.copy(ownerId = post.ownerId, fromId = post.fromId, text = post.text, can_edit = post.can_edit, date = post.date, likes = post.likes)
                return true
            }
        }
        return false
    }

    fun addLike(id:Int) {
        for ((index, post) in posts.withIndex()) {
            if (post.id == id) {
                posts[index] = post.copy(likes=Likes(post.likes.count+1, true, false))
            }
        }
    }

    fun getMaxPostId(): Int {
        var maxId=0;
        for (post in posts) {
            if (post.id > maxId) {
                maxId = post.id
            }
        }
        return maxId
    }

    fun getPostInfo(id:Int): String {
        var result = "Нет поста с таким ID"
        for (post in posts) {
            if (post.id == id) {
                result = "У поста `${post.text}` количество лайков = ${post.likes.count}"
            }
        }
        return result
    }

    fun clear() {
        posts = emptyArray()
    }
}