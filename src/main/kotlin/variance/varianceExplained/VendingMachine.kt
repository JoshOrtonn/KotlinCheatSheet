package variance.varianceExplained

import variance.CandyBar
import variance.Coin
import variance.Snack
// Variance describes the relationship between the container type (vending machine)
// And the associations it contains (Coin or Snack)

// So in this case
interface VendingMachine {
    fun purchase(money: Coin): Snack
}


// Called Co-variance
// Whereby below the purchase return type CandyBar is a subtype of snack
// ANDDD
// The return type SimpleVendingMachine is a subtype of VendingMachine
// Where co means together, so varying together.

// Hence relationship between
// container type (simpleVendingMachine) and one of its return types is called covariant
// As container type becomes more specific, so can the return type
// Note that coin cannot be more specific say dime type for example
// As other coins should be supported, and not type safe
// Should match all expected inputs from abstract
private class SimpleVendingMachine: VendingMachine {
    override fun purchase(money: Coin) = CandyBar()
}

// If we were to change Coin in simpleVendingMachine to be more general, like Money
// Then as VendingMachine subtypes becomre more specific, parameter types become more general
// THis is called contravariance
// Note in kotlin due to function overloading, we can't do this on Purchase function without
// still honouring the abstract purchase with coin declaration
// Hence can do with a val taking in a lambda, blah blah