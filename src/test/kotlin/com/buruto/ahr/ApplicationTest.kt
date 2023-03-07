package com.buruto.ahr

import com.buruto.ahr.models.ApiResponse
import com.buruto.ahr.repository.HeroRepository
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import org.koin.java.KoinJavaComponent.inject
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    private val heroRepository: HeroRepository by inject(clazz = HeroRepository::class.java)

    @Test
    fun `access root endpoint, assert correct information`() = testApplication {
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Welcome to Boruto API", bodyAsText())
        }
    }

    @org.junit.Test
    fun `access all heroes endpoint, get default access, access correct information`() = testApplication {

        environment {
            developmentMode = false
        }
        client.get("/boruto/heroes").apply {
            assertEquals(HttpStatusCode.OK, status)
            val expectedResponse = ApiResponse(
                success = true,
                message = "Success get heroes",
                prevPage = null,
                nextPage = 2,
                data = heroRepository.page1
            )
            val actualResponse = Json.decodeFromString<ApiResponse>(serializer(), bodyAsText())
            assertEquals(expectedResponse, actualResponse)
        }
    }

}
