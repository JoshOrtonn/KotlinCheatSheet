package variance.varianceExplained

import variance.CandyBar
import variance.Coin
import variance.Money
import variance.Snack

interface VendingMachinePrevExample {
    val purchase: (Coin) -> Snack
}

// Out keyword is declared, can only publicly appear as return type
// So subtype can have a covariant relationship with it
// But when type parameter declared with keyword `in`then can only appear
// as function parameter, which also means the container type can have a
// contravariant relationship with it.
interface VendingMachineExplain<in T, out R> {
    val purchase: (T) -> R
}


// As long as the one marked by in, is the same or more generic it's fine
// Or if the type is marked as out, then it needs to be the same or more specific
// Then perfectly type safety
class SnackVendingMachine<Coin, Snack> {}
class CandyBarVendingMachine<Money, CandyBar>


// SimpleVending machine is a subtype of VendingMachine
// But Money is a parameter supertype of coin so called Contravariance
// As becoming more specific in opposite directions
// Whereas return types can become more specific with the subtype,
// which is known as covariance
class SimpleVendingMachineExplain: VendingMachinePrevExample {
    override val purchase: (Money) -> Snack = { CandyBar() }
}

