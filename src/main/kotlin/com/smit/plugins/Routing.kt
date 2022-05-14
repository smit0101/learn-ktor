package com.smit.plugins

import com.smit.routes.customerRouting
import com.smit.routes.getOrderRoute
import com.smit.routes.listOrderRoute
import com.smit.routes.totalizeOrderRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
        customerRouting()
        listOrderRoute()
        getOrderRoute()
        totalizeOrderRoute()
    }
}
