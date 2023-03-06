package com.buruto.ahr.routes

import com.buruto.ahr.repository.HeroRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.searchHeroes() {

    val heroRepository by inject<HeroRepository>()

    get("/boruto/heroes/search") {
        val name = call.request.queryParameters["name"]
        val apiResponse = heroRepository.searchHeroes(name)
        call.respond(
            message = apiResponse,
            status = HttpStatusCode.OK
        )
    }
}