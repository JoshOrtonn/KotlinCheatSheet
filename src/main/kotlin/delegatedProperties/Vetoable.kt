package delegatedProperties

import kotlin.properties.Delegates

class Vetoable {
    // Returns a property delegate for a read/ write property
    // that calls a specified callback function when changed,
    // allowing the callback to veto the modification

    fun vetoDelegate() {

        // Could use vetoable for some
        // input validation checks before applying change to variable
        var stockPrice by Delegates.vetoable(0) { _, oldValue, newValue ->
            // we choose to veto the change, only if the below statement is true
            newValue > oldValue
            // Hence stock price only ever would increase
        }
        println(stockPrice)
        stockPrice++
        println(stockPrice)
        stockPrice++
        println(stockPrice)
        stockPrice++
        println(stockPrice)
        stockPrice = 0
        println(stockPrice)
        stockPrice++
        println(stockPrice)
        stockPrice--
        println(stockPrice)
    }
}