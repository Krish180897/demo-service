package example.com

import example.com.modules.todos.dto.UserData
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import kotlin.test.*

class ApplicationTest {
    @Test
    fun testGetUserById() = testApplication {
//        client.get("/users?id=1").apply {
//            println(status)
//            assertEquals(HttpStatusCode.OK, status)
//            val response = bodyAsText()
//            val userData = Json.decodeFromString<UserData>(response)
//            assertEquals(1, userData.id)
//        }
        val response = client.get("/todos/users?id=1")
        assertEquals(HttpStatusCode.OK, response.status)
        val userData = Json.decodeFromString<UserData>(response.body())
        assertEquals(1, userData.id)
    }

    @Test
    fun testGetAllUsers() = testApplication {
        client.get("/todos/users").apply {
            assertEquals(HttpStatusCode.OK, status)
            val response = bodyAsText()
            val userList = Json.decodeFromString<List<UserData>>(response)
            assertTrue(userList.isNotEmpty())
        }
    }
}