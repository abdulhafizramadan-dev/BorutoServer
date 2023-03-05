package com.buruto.ahr

import io.ktor.server.application.*
import com.buruto.ahr.plugins.*

fun main(args: Array<String>): Unit =
        io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureMonitoring()
    configureSerialization()
    configureRouting()
}
