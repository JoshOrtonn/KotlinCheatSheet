package coroutines

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import kotlin.coroutines.*


// Couroutines can suspend it's execution and can then resume at a later date
// preserving state within that coroutine scope.

fun main() {
    // Runs a new coroutine and blocks the current thread interruptibly until its completion.
    // Considered a coroutine builder
    runBlocking {
        // CoRoutine builder launches new coroutine without blocking
        launch {
            println("Sledge: Suplex!")
            // Code is yielding thread it's running on to any other thread
            // or could be considered tapping Hammer to jump in the ring
            tagOut()
            println("Sledge: Figure-four leglock!")
            tagOut()
            println("Sledge: Pin 1-2-3!")
        }
        launch {
            println("Hammer: Clothesline!")
            tagOut()
            println("Hammer: Piledriver!")
            tagOut()
        }
    }
}

// Any function calling any suspend function
// Will also need suspend keyword
suspend fun tagOut() {
    println("Tagging out!")
    yield()
}