package designPatterns.decoratorPattern.refactored

import java.io.Console
import java.time.Clock
import java.util.*

// Component
interface Logger {
    fun log(message: String)
}

// Concrete Component
class ConsoleLogger(
): Logger {
    override fun log(message: String) {
        println(message)
    }
}


// Concrete Decorator
class UniqueIdLogger(private val logger: Logger): Logger {
    override fun log(message: String) {
        logger.log("${UUID.randomUUID()} $message")
    }
}

class ThreadNameLogger(private val logger: Logger): Logger {
    override fun log(message: String) {
        logger.log(" ( on ${Thread.currentThread().name}) thread $message")
    }
}

class DateTimeLogger(private val logger: Logger, private val clock: Clock = Clock.systemDefaultZone()): Logger {
    override fun log(message: String) {
        logger.log("[${clock.instant()}] $message")

    }
}

// Perks of this is it's easy to extend a new type of log that works independently
// And can easily adapt to create a fileLogger, rather than console one.
// Only difficulty is ordering of log statement.
// Flexible to change.
fun main() {
    val logger = UniqueIdLogger(ThreadNameLogger(DateTimeLogger(ConsoleLogger())))
    logger.log("Application initialized!!")
}