package pl.msiwak.multiplatform

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}