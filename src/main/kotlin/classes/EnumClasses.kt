package classes

// All instances of an enum class are contained within the body of an enum class.
// Each instance called an entry.
// Great for limiting values that pertain to a type, like statuses of an order
enum class OrderStatus {
    PREPARING,
    READY_FOR_PICKUP,
    OUT_FOR_DELIVERY,
    DELIVERED
}

class EnumClasses {
    fun main() {
        val myStatus = OrderStatus.PREPARING

        OrderStatus.entries.forEach {
            println("${it.ordinal} - ${it.name}")
        }
        /*
        0 - PREPARING
        1 - READY_FOR_PICKUP
        2 - OUT_FOR_DELIVERY
        3 - DELIVERED
         */

        println(getMessage(myStatus))

    }

    // Neat thing about enum is that they can be exhaustively matched
    // Using a when condition there's no need for an else case
    // Great for limiting number of instances of a set known values.
    fun getMessage(status: OrderStatus) = when(status){
        OrderStatus.PREPARING -> "Order Cancelled"
        OrderStatus.READY_FOR_PICKUP -> "Order Ready"
        OrderStatus.OUT_FOR_DELIVERY -> "Order Delivery"
        OrderStatus.DELIVERED -> "Order Delivered"
    }

}