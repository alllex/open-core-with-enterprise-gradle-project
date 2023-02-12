package me.alllex.ocwe

class EnterpriseExtensionsImpl : EnterpriseExtensions {
    override fun getExtensions(): List<MyService> = listOf(Service1(), Service2())

    class Service1 : MyService {
        override fun greet() = "Enterprise Service 1"
    }

    class Service2 : MyService {
        override fun greet() = "Enterprise Service 2"
    }
}
