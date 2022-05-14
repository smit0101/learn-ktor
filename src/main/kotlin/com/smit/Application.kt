package com.smit

import com.smit.plugins.configureRouting
import com.smit.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        configureRouting()
        configureSerialization()
        install(CORS){
            anyHost()
        }
    }.start(wait = true)

}


