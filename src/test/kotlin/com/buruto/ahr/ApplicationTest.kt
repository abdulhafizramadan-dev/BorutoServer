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

    @Test
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

    @Test
    fun `access all heroes endpoint, get first page, access correct information`() = testApplication {

        environment {
            developmentMode = false
        }
        client.get("/boruto/heroes?page=1").apply {
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

    @Test
    fun `access all heroes endpoint, get out of range page, get error`() = testApplication {

        environment {
            developmentMode = false
        }
        client.get("/boruto/heroes?page=100").apply {
            assertEquals(HttpStatusCode.BadRequest, status)
            val expectedResponse = ApiResponse(
                success = false,
                message = "Heroes not found!"
            )
            val actualResponse = Json.decodeFromString<ApiResponse>(serializer(), bodyAsText())
            assertEquals(expectedResponse, actualResponse)
        }
    }

    @Test
    fun `access all heroes endpoint, get wrong type page, get error`() = testApplication {

        environment {
            developmentMode = false
        }
        client.get("/boruto/heroes?page=first").apply {
            assertEquals(HttpStatusCode.BadRequest, status)
            val expectedResponse = ApiResponse(
                success = false,
                message = "Only number allowed!"
            )
            val actualResponse = Json.decodeFromString<ApiResponse>(serializer(), bodyAsText())
            assertEquals(expectedResponse, actualResponse)
        }
    }

}
