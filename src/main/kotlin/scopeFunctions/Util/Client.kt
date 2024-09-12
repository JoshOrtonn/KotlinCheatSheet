package scopeFunctions.Util

class Client {
    fun connect() = println("c...")
    fun authenticate() = println("a...")
    fun getData() = "Mock Data: $token"

    var token: String = ""
}