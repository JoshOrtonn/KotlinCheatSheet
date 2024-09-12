package loops

class LoopBestPractices {
    val fruits = listOf("Apple", "Banana", "Orange", "Pear")

    fun rangeToLoop() {
        // .. (rangeTo) operator is inclusive so need the minus 1
        // as would be index out of bounds.
        for (index in 0..(fruits.size - 1)) {
            val fruit = fruits[index]
            println("$index: $fruit")
            /*
            0: Apple
            1: Banana
            2: Orange
            3: Pear
             */
        }
    }

    fun rangeUntilLoop() {
        // No longer needs to minus the size, as it's exclusive
        // And given index starts at 0, and size starts with 1, it works
        // But still a better way of doing this
        for (index in 0 until fruits.size) {
            val fruit = fruits[index]
            println("$index: $fruit")
        }
    }

    fun rangeToLastIndex() {
        // But perhaps doing it to the last index is closer to the actual meaning
        for (index in 0 .. fruits.lastIndex) {
            val fruit = fruits[index]
            println("$index: $fruit")
        }
    }

    fun indicesLoop() {
        // Returns an IntRange of the valid indices for this collection.
        for (index in fruits.indices) {
            val fruit = fruits[index]
            println("$index: $fruit")
        }
    }

    fun indexWithValueLoop() {
        // .withIndex Returns a lazy Iterable that wraps each element of the
        // original collection into an IndexedValue containing
        // the index of that element and the element itself.
        // Bit more elegant and expressive.
        // More like a forEach loop

        for ((index, fruit) in fruits.withIndex()) {
            println("$index: $fruit")
        }
    }

    fun forEachIndexLoop() {
        fruits.forEachIndexed { index, fruit ->
            println("$index: $fruit")
        }
    }


}