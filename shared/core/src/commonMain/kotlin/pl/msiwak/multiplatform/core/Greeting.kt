package pl.msiwak.multiplatform.core

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}