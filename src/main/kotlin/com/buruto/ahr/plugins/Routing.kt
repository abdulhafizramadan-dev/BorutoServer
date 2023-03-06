package com.buruto.ahr.plugins

import com.buruto.ahr.routes.configureStaticContent
import com.buruto.ahr.routes.getAllHeroes
import com.buruto.ahr.routes.root
import com.buruto.ahr.routes.searchHeroes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        configureStaticContent()
        root()
        getAllHeroes()
        searchHeroes()
    }
}
