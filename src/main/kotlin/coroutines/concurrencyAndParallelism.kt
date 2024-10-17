package coroutines

import kotlinx.coroutines.*

suspend fun order(item: Product): Product {
    println("ORDER EN ROUTE >>> the ${item.description} are on the way!")
    // Instead of Thread.sleep (which really just blocks the thread for that time
    // delay will yield the thread for it to do other work, and suspend the function
    // Until the specified time has reached, it will step back in.
    delay(item.deliveryTime)
    println("ORDER DELIVERED >>> Your ${item.description} has arrived.")
    return item
}

suspend fun perform(taskName: String) {
    println("STARTING TASK >>> $taskName")
    delay(1_000)
    println("FINISHING TASK >>> $taskName")
}


enum class Product(val description: String, val deliveryTime: Long) {
    DOORS("doors", 750),
    WINDOWS("windows", 1_250)
}

fun main() {
    //    slowSerialism()
    // Coroutines with concurrency  (two tasks interleaved over a period of time):
    //    concurrency()

    // With parallelism two tasks at same moment:
    // parallelism()
    parallelismWithException()
}

private suspend fun slowSerialism() {
    /* Slow version */
    val windows = order(Product.WINDOWS)
    val doors = order(Product.DOORS)
    perform("laying bricks")
    perform("installing ${windows.description}")
    perform("installing ${doors.description}")
}

private fun concurrency() {
    runBlocking {
        // Launch keyword is unable to assign return statement to val
        // Instead need to use async, perfect for non-dependent API calls
        // 3 coroutines, one for ordering windows, one for ordering doors, done asynchrounously
        // And another one for building bricks, windows and then doors, but this could be improved further.
        val windows = async { order(Product.WINDOWS) }
        val doors = async { order(Product.DOORS) }
        launch {
            perform("laying bricks")
            // Need to await for this response
            // Then the couroutine will suspend until value is ready
            perform("installing ${windows.await().description}")
            perform("installing ${doors.await().description}")
        }
    }
}

// Hierarchy of structured concurrency created
/*
                run Blocking

    async         | async       | launch
    Order windows | Order Doors | Lay Bricks

                                    | Launch   | Launch
                                      install    install doors
                                      windows
 */
private fun parallelism() {
    runBlocking {
        // Dispatchers assign calls to certain thread pools
        // Different dispatchers have CPU heavy or IO heavy with different Scaling characteristics
        // 2 coroutines for ordering windows and doors
        val windows = async(Dispatchers.IO) { order(Product.WINDOWS) }
        val doors = async(Dispatchers.IO) { order(Product.DOORS) }

        launch(Dispatchers.Default) {
            perform("Laying bricks")
            // Also added two more launches, to work in parallel to install windows and doors at same time.
            launch { perform("install ${windows.await().description}") }
            launch { perform("install ${doors.await().description}") }
        }
    }
}



/*
Once day the client says we need to cancel the whole project
So Dusty told all the bots to cancel one by one.
 */
private fun parallelismWithCancellation() {
    runBlocking {
        // Dispatchers assign calls to certain thread pools
        // Different dispatchers have CPU heavy or IO heavy with different Scaling characteristics
        val windows = async(Dispatchers.IO) { order(Product.WINDOWS) }
        val doors = async(Dispatchers.IO) { order(Product.DOORS) }
        launch(Dispatchers.Default) {
            perform("Laying bricks")
            // Also added two more launches, to work in parallel to install windows
            launch { perform("install ${windows.await().description}") }
            launch { perform("install ${doors.await().description}") }
        }
        // Cancel at root level will propagate down the hierarchy to cancel them all
        // Use case is user opens page on app to downloasd document,
        // Document starts downloading, and then after 2 seconds they get bored
        // Then they swipe away, instead of continuing the download
        // Wastefully using network resources, we can cancel instead
        cancel()
    }
}

/*
Once day the bot says we have run out of money and need to stop ordering any more!!
So we simulate an exception
 */
private fun parallelismWithException() {
    runBlocking {
        // Dispatchers assign calls to certain thread pools
        // Different dispatchers have CPU heavy or IO heavy with different Scaling characteristics
        val windows = async(Dispatchers.IO) { order(Product.WINDOWS) }
        val doors = async(Dispatchers.IO) {
            // Exception thrown at async level in the tree, it'll go to parent
            // Cancel at root level, which will then cancel all of it's children.
            throw Exception("Out of Money!!")
            order(Product.DOORS)
        }
        launch(Dispatchers.Default) {
            perform("Laying bricks")
            // Also added two more launches, to work in parallel to install windows
            launch { perform("install ${windows.await().description}") }
            launch { perform("install ${doors.await().description}") }
        }
    }
}

