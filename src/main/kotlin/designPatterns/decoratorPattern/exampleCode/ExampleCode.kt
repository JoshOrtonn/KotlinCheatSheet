package designPatterns.decoratorPattern.exampleCode

import java.time.Clock
import java.util.*

// As we add more and more features to our logger,
// We'll get more and more params to class
// And gonna just expand
// Also clock is passed, even if includeDateTime is false
// print + println() only send to console.
class Logger(
    private val includeDateTime: Boolean,
    private val includeThreadName: Boolean,
    private val includeUniqueId: Boolean,
    private val clock: Clock
) {
    fun log(message: String) {
        if(includeDateTime) print("[${clock.millis()}]")
        if(includeUniqueId) print("{${UUID.randomUUID()}}")
        print(message)
        if(includeThreadName) print(" ( on ${Thread.currentThread().name}) thread")
        println()
    }
}

fun main() {
    val logger = Logger(
        includeThreadName = true,
        includeDateTime = true,
        includeUniqueId = true,
        clock = Clock.systemUTC()
    )

    logger.log("Application initialized!!")
}