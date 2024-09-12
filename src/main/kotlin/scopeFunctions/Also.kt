package scopeFunctions

// Execute operations and pass the original value through
class Also {
    // Often use also for side affect during longer chain of calls
    // Something like Logging, or inspecting value at certain point
    // Receives value and passes downstream to next receiver.
    fun alsoExample() {
        val medals = listOf("Gold", "Silver", "Bronze")
        val reversedLongUppercaseMedals = medals
            .map { it.uppercase() }
            .also { println("Logging and analytics") }
            .filter { it.length > 4 }
            .also { println("$it: before being reversed")  }
            .reversed()

        println(reversedLongUppercaseMedals)
    }

}