package variance.TypeProjection

import variance.CandyBar
import variance.Dime
import variance.Money
import variance.Snack


// Previously was <out T: Product>
class VendingMachineOutDemo<T: Product, Product>(private val product: T) {
    fun purchase(money: Money): T = product
    // If we were to add a new function called refund
    // Then type parameter is going to show up as both return type (for functions)
    // And also as a parameter type.
    // And given we are using out keyword, it can only be used as return
    fun refund(product: T): Money = Dime()

    // Therefore we could remove the out modifier, for it's use as param type too
    // But then VendingMachine<CandyBar> won't be a subtype of VendingMachine<Snack>

    // Thankfully Kotlin gives us the use of "Type Projection"
    // To use type projection we put variance modifier
    // on type argument instead of a type parameter
    // So we will add out to VendingMachine<Snack>

}

val candyBarMachine: VendingMachineOutDemo<CandyBar, Any?> = VendingMachineOutDemo(CandyBar())
// Previously was VendingMachineDemo<Snack>
val snackMachine: VendingMachineOutDemo<out Snack, Any?> = candyBarMachine
// With the out modifier the snackMachine variable has a type that
// is a projection of VendingMachine<Snack>
// VendingMachine<out snack>, is an out type projection
// Looks similar to VendingMachine<Snack> but every function where the type param
// Appeared in the in-position is going to be changed to it's more restrictive
// Hence refund(product: T) where this is a type parameter, it's changed from
// refund(product: Snack): Money => refund(product: Nothing): Money
// Note that purchase remains unchanged
/*
Type mismatch. Required: Nothing Found: CandyBar
 */
//val result = snackMachine.refund(CandyBar())
// Nothing type is the subtype of every other type
// no way to create an instance of it
// But using different vending machine we can get refund just fine
val result = candyBarMachine.refund(CandyBar())


// What is type projection?
// Consider shining light on beach ball against a wall
// Projected behind it is a 2D shadow of similar shape on the wall
// But it's lost the 3D depth
