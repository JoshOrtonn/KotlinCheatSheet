package collectionDataTypes

class ListChunked {
    fun listChunkedAndWindowed(){
        val students = listOf("Ann", "Joe", "Eve", "Lea", "Max")

        // Chunks off individual elements with no overlap
        /* Splits this collection into a list of lists each not exceeding the given [size].
        The last list in the resulting list may have fewer elements than the given [size]
         */
        println(students.chunked(2))
        // Will output:
        // [[Ann, Joe], [Eve, Lea], [Max]

        // Sliding window, with list size, will drop any smaller than 3 sliding window
        println(students.windowed(3))
        // Will output:
        // [[Ann, Joe, Eve], [Joe, Eve, Lea], [Eve, Lea, Max]


        // and some latter lists may have fewer if partialWindows is true
        println(students.windowed(3, partialWindows = true))
        // Will output:
        // [[Ann, Joe, Eve], [Joe, Eve, Lea], [Eve, Lea, Max], [Lea, Max], [Max]]

        val temps = listOf(17.0, 12.4, 18.5, 19.8, -13.0)
        val slidingAverage = temps.windowed(3) {
            it.average()
            // alternative is:
            // val (a, b, c) = it
            // (a + b + c) / 3
        }
        println(slidingAverage)
        // output: [15.966666666666667, 16.900000000000002, 8.433333333333332]

    }
}