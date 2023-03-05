package com.buruto.ahr.routes

import com.buruto.ahr.models.ApiResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.getAllHeroes() {
    get("/boruto/heroes") {
        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1
            require(page in 1..5)
            call.respond(message = page)
        }
        catch (exception: NumberFormatException) {
            call.respond(
                message = ApiResponse(
                    success = false,
                    message = "Only number allowed!"
                ),
                status = HttpStatusCode.BadRequest
            )
        }
        catch (exception: IllegalArgumentException) {
            call.respond(
                message = ApiResponse(
                    success = false,
                    message = "Heroes not found!"
                )
            )
        }

    }
}