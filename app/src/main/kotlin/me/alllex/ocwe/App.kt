package me.alllex.ocwe

import java.util.ServiceLoader

fun main() {
    val openSourceServices = listOf(OpenSourceService())
    val enterpriseExtensionServices = ServiceLoader.load(EnterpriseExtensions::class.java).toList()
        .flatMap { it.getExtensions() }
    val allServices = openSourceServices + enterpriseExtensionServices

    println("Loaded ${allServices.size} services")
    for (myService in allServices) {
        println("- ${myService.greet()}")
    }
}

interface MyService {
    fun greet(): String
}

class OpenSourceService : MyService {
    override fun greet() = "Open Source service"
}

interface EnterpriseExtensions {
    fun getExtensions(): List<MyService>
}
