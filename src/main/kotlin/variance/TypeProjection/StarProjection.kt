package variance.TypeProjection

import variance.*

class VendingMachineStar<T: Product>(private val product: T){
    fun purchase(money: Money): T = product
    fun refund(product: T): Money = Dime()
    // No type param at all
    // ( either (out position) return or (in position) param type)
    fun performanceMaintainence() = println("All tuned up!")
}

fun fakeMain() {
    val snackMachine: VendingMachineStar<Snack> = VendingMachineStar(Snack.random())
    val candyBarMachine: VendingMachineStar<CandyBar> = VendingMachineStar(CandyBar())

   /*
    var anyMachine: VendingMachineStar<Product>
    anyMachine = snackMachine
//    Type mismatch. Required:VendingMachineStar<Product> Found: VendingMachineStar<Snack>
    anyMachine = candyBarMachine
    //    Type mismatch. Required:VendingMachineStar<Product> Found: VendingMachineStar<Snack>

    anyMachine.performanceMaintainence()
    */


    // Instead we can use star projection which looks like this:
    var anyMachine: VendingMachineStar<*>

/*
    VendingMachine<Snack> =
    + purchase(money: Money): Snack
    + refund(product: Snack): Money
 */
    // Will be projected to:
/*
    VendingMachine<*> =
    + purchase(money: Money): Product
    + refund(product: Nothing): Money
*/

    // Therefore every occurence of type param with in position will be
    // replace with Nothing
    // And every occurence of type param with out position will be
    // replaced with type params upperbound (Product)

    anyMachine = snackMachine
    anyMachine = candyBarMachine
    anyMachine.performanceMaintainence()


    // Could have instead changed to our projection on type Product
    // But star allows for any type argument of vending machine, and sets intent on that
    // Type argument is largely irrevevant
    // And stars are more resilent to change of upper bound Product to Snack perhaps.
    
}