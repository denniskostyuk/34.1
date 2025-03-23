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
}