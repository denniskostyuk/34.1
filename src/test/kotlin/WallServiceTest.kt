import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Test
    fun addPost() {

        val service = WallService
        val result = service.addPost(
            Post(
                ownerId = 1,
                fromId = 1,
                text = "Hello World Again",
                can_edit = true,
                date = 1742682123,
                likes = Likes(0, false, true)
            )
        ).id

        assertEquals(1, result)
    }

    @Test
    fun updateTrue() {

        val service = WallService
        val result = service.update(Post(1,3,3,"This is new text", true, 1742682999, Likes(999, false,true)))
        assertEquals(true, result)
    }

    @Test
    fun updateFalse() {

        val service = WallService
        val result = service.update(Post(10,3,3,"This is new text", true, 1742682999, Likes(999, false,true)))
        assertEquals(false, result)
    }


}