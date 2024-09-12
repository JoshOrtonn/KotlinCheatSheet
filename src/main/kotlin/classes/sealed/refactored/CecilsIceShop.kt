package classes.sealed.refactored

enum class Size { CUP, BUCKET, BAG }

// Sealed class by definition is also an abstract class
// Meaning cannot directly instantiate it, but only instantiate one of its subclasses
sealed class Request { val id: Int = kotlin.random.Random.nextInt() }

// + Sealed types helpful when you want kotlin to ensure you exhaustivel match subtypes in a when conditional
// - Must be declared in the same code base.
//    - If to use this sealed type in the library, would not be able to subtype it.
// - Must be declared in the same package


class OrderRequest(val size: Size) : Request()
class RefundRequest(val size: Size, val reason: String = "") : Request()
class SupportRequest(val text: String) : Request()

object FrontDesk {
    fun recieve(request: Request) {
        println("Handling request #${request.id}")
        when (request) {
            is OrderRequest -> IceCubeFactory.fulfillOrder(request)
            is RefundRequest -> IceCubeFactory.fulfillRefund(request)
            is SupportRequest -> HelpDesk.handle(request)
        }
    }

}
object HelpDesk {
    fun handle(request: SupportRequest) = println("Help desk is handling ${request.id}")
}

object IceCubeFactory {
    fun fulfillOrder(order: OrderRequest) = println("Fulfilling order #${order.id}")
    fun fulfillRefund(refund: RefundRequest) = println("Fulfilling refund #${refund.id}")
}

fun main() {
    val order = OrderRequest(Size.CUP) //Handling request #123
    FrontDesk.recieve(order) // Fulfilling order #123

    val refund = RefundRequest(Size.CUP) //Handling request #456
    FrontDesk.recieve(refund) // Fulfilling order #456

    val request = SupportRequest("I can't open the bag of ice!")
    FrontDesk.recieve(request) // Handling request #789.
}
