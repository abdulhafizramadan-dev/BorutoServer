package com.buruto.ahr

import io.ktor.server.application.*
import com.buruto.ahr.plugins.*

fun main(args: Array<String>): Unit =
        io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureKoin()
    configureMonitoring()
    configureSerialization()
    configureRouting()
    configureDefaultHeader()
}
