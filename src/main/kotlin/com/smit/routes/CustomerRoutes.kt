package com.smit.routes

import com.smit.models.Customer
import com.smit.models.customerStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.customerRouting() {
    route("/customer") {
        get {
            if (customerStorage.isNotEmpty()) {
                call.respond(customerStorage)
            } else {
                call.respondText(
                    "No Customer Found",
                    status = HttpStatusCode.OK
                )
            }
        }
        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing ID",
                status = HttpStatusCode.BadRequest
            )
            val customer = customerStorage.find { it.id == id } ?: return@get call.respondText(
                "No Customer With ID $id ",
                status = HttpStatusCode.NotFound
            )
            call.respond(customer)
        }
        post {
            val customer = call.receive<Customer>()
            customerStorage.add(customer)
            call.respondText("Customer stored correctly", status = HttpStatusCode.Created)
        }
        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respondText("Bad Request",
            status = HttpStatusCode.BadRequest)
            if(customerStorage.removeIf{it.id == id}){
                call.respondText("Customer Removed Correctly", status = HttpStatusCode.Accepted)
            }else{
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
    }
}