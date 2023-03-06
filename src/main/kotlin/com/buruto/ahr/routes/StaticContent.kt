package com.buruto.ahr.routes

import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Routing.configureStaticContent() {
    static(remotePath = "images") {
        resources("images")
    }
}