package com.buruto.ahr.plugins

import com.buruto.ahr.routes.getAllHeroes
import com.buruto.ahr.routes.root
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        root()
        getAllHeroes()
    }
}
