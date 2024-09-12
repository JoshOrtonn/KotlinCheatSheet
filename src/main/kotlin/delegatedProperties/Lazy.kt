package delegatedProperties

class Lazy {
    private fun someLongComputation(): Int {
        println("Computing..... For ages.....")
        return 42
    }


    // Specific use case would be loads of code so many branches,
    // But only this computation is needed in specific branches
    // hence not wasting time doing this computation for it to not be used
    fun lazyDelegate(){
        // This is only ever computed if price is accessed.
        val price by lazy { someLongComputation() }

        println("Price is $price")
        // Acts like a normal read only variable, and only computed once
        // And even works in a thread safe manner
        println("Price is $price")
        println("Price is $price")
        println("Price is $price")

    }
}