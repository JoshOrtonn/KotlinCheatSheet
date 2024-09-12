package extensionFunctions

// Invoke allows objects to be callable.
// Useful for maybe wanting to create a new context like this math problem below.
// Where the invocation actually multiplies the two numbers together, like maths algebra should
class Invoke {

    fun mathsProblem() {
        operator fun Int.invoke(other: Int) = this * other

        val result = 3(2+5)

        println(result)
        // 21
    }

    //
    fun otherFunUseCases() {
        operator fun String.invoke() = println(this)
        operator fun Any?.invoke() = println("Printing $this")

        1()
        "Hello World"()
        null()
        /*
            Printing 1
            Hello World
            Printing null
         */
    }
}