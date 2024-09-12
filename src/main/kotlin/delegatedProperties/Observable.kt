package delegatedProperties

import kotlin.properties.Delegates

// Used to apply side effects during specific observed changes on a property
class Observable {
    fun observeDelegate() {
        var stockPrice by Delegates.observable(0) { property, oldValue, newValue ->
            if(newValue > oldValue) println("Stock price increased, tell the CEO")
            else println("Stay quiet")
        }

        stockPrice++
        stockPrice++
        stockPrice++
        stockPrice = 0
        stockPrice++
        stockPrice--
    }
}