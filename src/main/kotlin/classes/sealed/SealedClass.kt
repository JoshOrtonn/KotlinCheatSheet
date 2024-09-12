package classes.sealed

import classes.OrderStatus

// If you need to limit the subtypes of a class
// Rather than the instances of a class.
// Consider using sealed class.
// Often see subclasses are nested inside the sealed class like this.
// But doesn't have to be, just as long as it's within the same module and package
// Often used to handle API calls to different endpoints for handling state returned
// Sealed classes great for constraining subclasses to known subtypes

// Sealed
sealed class ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Failure(val ex: Exception): ApiResponse<Nothing>()
    data object Processing: ApiResponse<Nothing>()
}

// Kotlin can take all direct subclasses of ApiResponse and exhaustively
// match them in a when expression

fun handleResult(result: ApiResponse<OrderStatus>) {
    when (result) {
        is ApiResponse.Success -> println("Order was placed: ${result.data}")
        is ApiResponse.Failure -> println("Something went wrong: ${result.ex}")
        is ApiResponse.Processing -> println("Please stand by")
    }
}
