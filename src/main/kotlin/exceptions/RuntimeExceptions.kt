package exceptions

val ordinals = listOf("zeroth", "first", "second", "third", "fourth", "fifth")

fun ordinal(number: Int) = ordinals.get(number)

fun annc(number: Int, task: String): String {
    val ordinal = ordinal(number)
    if("clean" in task) throw HolidayException(task)

    return "The $ordinal thing I will do is $task"
}
class HolidayException(val task: String) : Exception("'$task' is not allowed on holidays!")
val exception = Exception("No cleaning allowed on holidays!!")

fun main() {
    val tasks = listOf(1 to "clean my room", 9 to "take out trash", 5 to "feed the dog")

    errorHandling(tasks)

    runCatching(tasks)
}

private fun runCatching(tasks: List<Pair<Int, String>>) {
    tasks.forEach { (number, task) ->
        // If annc() completes successfully result will contain result of annc
        // If annc throws an exception, the result will contain that exception
        // Does not allow you to deal with exceptions differently, black box deals with it
        val result = runCatching { annc(number, task) }

        val text = result.getOrDefault("Something went wrong!")
        println(text)
    }
}


// More expressive and can handle different exceptions
// And can add a finally block too.
fun errorHandling(tasks: List<Pair<Int, String>>) {
    try {
        tasks.forEach { (number, task) ->
            println(annc(number, task))
        }
    } catch (e: HolidayException) {
        println("It's a holiday! I'm not going to ${e.task} today!")
    } catch (e: ArrayIndexOutOfBoundsException) {
        println("I can't count that high!")
    } catch (e: Exception) {
        println("I wasn't expecting this!")
    } finally {
        // close some file
        // clean up resources
    }
}