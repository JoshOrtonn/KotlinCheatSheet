package classes.sealed.exampleCode

enum class Size { CUP, BUCKET, BAG }

interface Request { val id: Int }

class OrderRequest(override val id: Int, val size: Size) : Request
class RefundRequest(override val id: Int, val size: Size, val reason: String = "") : Request
class SupportRequest(override val id: Int, val text: String) : Request

object FrontDesk {
    fun recieve(request: Request) {
        println("Handling request #${request.id}")
        when (request) {
            is OrderRequest -> IceCubeFactory.fulfillOrder(request)
            is RefundRequest -> IceCubeFactory.fulfillRefund(request)
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
    val order = OrderRequest(123, Size.CUP) //Handling request #123
    FrontDesk.recieve(order) // Fulfilling order #123

    val refund = RefundRequest(456, Size.CUP) //Handling request #456
    FrontDesk.recieve(refund) // Fulfilling order #456

    val request = SupportRequest(789, "I can't open the bag of ice!")
    FrontDesk.recieve(request) // Handling request #789
    // But nothing else happens
    // Cecil the seal was very unhappy with no progress, he has not supplied
    // Another conditional to the when, which should be exhaustive
    // But was based on the interface, not the sealed type...
}
