package designPatterns.decoratorPattern.kotlinModernized

import java.io.Console
import java.time.Clock
import java.util.*

// functional interface
fun interface Logger {
    fun log(message: String)
}

// "It" refers to message string parameter as only single one
// Or can be explicit message like below
val consoleLogger = Logger { message: String -> println(message)}


// More of a kotlin way with extension function, rather than class based.
fun Logger.withUniqueId() = Logger { message: String ->  log("${UUID.randomUUID()} $message")}
fun Logger.withThreadName() = Logger { message: String ->  log(" ( on ${Thread.currentThread().name}) thread $message")}
fun Logger.withDateTime(clock: Clock = Clock.systemDefaultZone()) = Logger { message: String -> log("[${clock.instant()}] $message")}


// Perks of this is it's easy to extend a new type of log that works independently
// And can easily adapt to create a fileLogger, rather than console one.
// Only difficulty is ordering of log statement.
// Flexible to change.
fun main() {
    val logger = consoleLogger.withUniqueId()
        .withThreadName()
        .withDateTime()

    logger.log("Application initialized!!")
}