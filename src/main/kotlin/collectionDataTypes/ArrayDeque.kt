package collectionDataTypes

import kotlin.collections.ArrayDeque

class ArrayDeque {

    fun dequeIntro() {
        // Pronounced deck.
        // Double ended queue
        // Can effectively think of as mutableList which
        // offers supported functions for first, last, and
        // mutating either addFirst, addLast / remove.
        val deque: ArrayDeque<Int> = ArrayDeque<Int>()

        // Built in function for Collections.ArrayDeque's
        // Aware of use case of inserting more elements
        // at either end.
        // Holds memory in a way that can more performantly
        // Add new indexes at first and last position.
        deque.addFirst(1)

        // Slower than above as have to copy everything
        // that previously existed, create new array
        // with new index and value, pasting previous values
        deque.add(0, 1)

        deque.addLast(5)

    }

}