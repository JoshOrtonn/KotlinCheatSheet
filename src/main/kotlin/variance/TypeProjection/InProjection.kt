package variance.TypeProjection

import variance.*


class VendingMachineDemoIn<T: Product>(private val product: T){
    fun purchase(money: Money): T = product
    fun refund(product: T): Money = Dime()
}

// So now snackVendingMachine is a subtype of candyBarVending Machine
// And use in modifier to make an in-projection change CandyBar
// Known as Use-Site Variance
val snackMachineIn: VendingMachineDemoIn<Snack> = VendingMachineDemoIn(CandyBar())
val candyBarMachineIn: VendingMachineDemoIn<in CandyBar> = snackMachineIn

/* in projection looks like the original snack type, from:
+ purchase(money: Money): Snack
+ refund(product: Snack): Money
 to:
+ purchase(money: Money): Any?
+ refund(product: Snack): Money

Meaning that the return type of functions using the type parameter
will change to nullable Any? (which is a supertype of every single type)
but the parameter type remains the same

As a result, it's now type safe for candyBar purchase to be a supertype of the snack
and we have covariance occuring as snack machine will be more specific than candyBar
and it's safe for subtyping to happen.
*/

val anyCandyBar: CandyBar? = candyBarMachineIn.purchase(Dime()) as? CandyBar