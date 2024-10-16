package algoCheatSheet

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap

class Types {
    fun charCheats() {
        val char = 'A'

        // Code value - chat.toInt() returns the same, but it is deprecated
        val code = char.code

        // Digit value
        val digit = char.digitToInt() // digitToIntOrNull() for nullable result

        // Char type
        val isDigit = char.isDigit()
        val isLetter = char.isLetter()

        // Letter case
        val isUpperCase = char.isUpperCase() // isLowerCase()
        val upperCase = char.uppercaseChar() // uppercase() which returns String
    }

    fun stringCheats() {
        val string = "ABC"

        val length = string.length // not size
        val substring = string.substring(0, 6) // [from, to) - from inclusive, to exclusive
        val split: List<String> = string.split(":", ",", "-")
        val join = listOf("abc", "dfg", "123").joinToString(separator = ", ", prefix = "", postfix = "")
        val replaced = "abc123abc".replace("ab", "qw") // -> "qwc123qwc"
    }

    fun indexing() {
        val list = listOf<Int>()

        val first = list.first() // works with List, Array, String
        val last = list.last()

        val lastIndex = list.lastIndex // works with List, Array, String

        list.forEach { item -> } // works with List, Array, String
        list.forEachIndexed { index, item -> }
        (0 until list.size).forEach { index -> } // alternatives 0..list.lastIndex or list.indices
        (0..list.lastIndex).forEach { index -> } // Same as above
        list.indices.forEach { index -> } // same as above
        list.indices.reversed().forEach { index -> } // reversed indexing
        (5 downTo 0).forEach { index -> } // 5 4 3 2 1 0
    }

    fun array() {
        val array = arrayOf(1, 2, 3)
        val arrayInt = Array<Int>(3) { i -> i } // 1, 2, 3
        val arrayNullableInt: Array<Int?> = arrayOfNulls(3) // null null null
        val list = array.toList()
        val listToArray: Array<Int> = list.toTypedArray()
        val intArray = IntArray(3) // 0 0 0 - primitive int array
    }

    fun map() {
        val map = mutableMapOf<String, Int>()  // returns LinkedHashMap, maintains insertion order, but mutable
        val hashMap = HashMap<String, Int>() // returns HashMap, not maintains insertion order

        val default = map.getOrDefault("key", 0)

        // Immutable once built.
        val builderMap: Map<String, Int> = buildMap<String, Int> {
            put("key", 0)
            put("key1", 1)
            put("key2", 2)
            put("key3", 3)
        }
    }

    fun list() {
        // Be careful when you need mutable and immutable lists
        val list = listOf(1, 2, 3) // immutable
        val mutableList = mutableListOf(1, 2, 3) // mutable

        // Most operations similar for mutable and immutable,
        // Immutable so not in place, hence new list is returned.
        val reversed = list.reversed()

        // Mutable returns in place of existing.
        val newList = mutableList.reverse() // in-place
    }
    fun linkedListAsQueue() {
        // FIFO
        //LinkedList in Kotlin acts as a doubly linked list, where each node has references to the previous and next nodes.
        // This structure allows for efficient insertion and removal of elements at both ends,
        // making it ideal for implementing queues where elements are frequently added and removed.

        // Demonstrating enqueue, dequeue, and peek operations
        val queue = LinkedList<String>()
        queue.add("First")  // Enqueue
        queue.add("Second")
        // Queue is: [First, Second]
        println(queue.peek())  // Peek the first element, but does not remove: "First" (null if empty)
        // queue is still [First, Second]

        println(queue.poll())  // Dequeue the first element: "First" (null if empty)
        // OR:
        // println(queue.remove()) //  Dequeue the first element: "First" (NoSuchElementException if empty)
        //Queue becomes: [Second]

        println(queue.isEmpty())  // Check if the queue is empty: false
    }

    fun linkedListAsStack() {
        // LIFO
        // LinkedList in Kotlin acts as a doubly linked list, where each node has references to the previous and next nodes.
        // This structure allows for efficient insertion and removal of elements at both ends,
        // making it ideal for implementing queues where elements are frequently added and removed.

        // Demonstrating enqueue, dequeue, and peek operations
        val queue = LinkedList<String>()
        queue.push("First")  // Enqueue
        queue.push("NewFirst")
        // Queue is: [NewFirst, First]
        println(queue.peek())  // Peek the first element, but does not remove: "NewFirstFirst" (null if empty)
        // queue is still [NewFirst, First]

        println(queue.poll())  // Dequeue the first element: "NewFirst" (null if empty)
        // OR:
        // println(queue.pop()) //  Dequeue the first element: "NewFirst" (NoSuchElementException if empty)
        //Queue becomes: [First]

        println(queue.isEmpty())  // Check if the queue is empty: false
    }


    fun arrayDeckAsQueue() {
        // FIFO
        // Demonstrating enqueue, dequeue, and peek operations
        val queue = ArrayDeque<String>()
        queue.addLast("First")  // Enqueue in last position
        queue.addLast("Second")
        // Queue is: [First, Second]
        println(queue.firstOrNull())  // Peek the first element, but does not remove: "First" (null if empty)
        // queue is still [First, Second]

        println(queue.removeFirstOrNull())  // Dequeue the first element: "First" (null if empty)
        // OR:
        // println(queue.removeFirst()) // Dequeue the first element: "First" (NoSuchElementException if empty)
        // Queue becomes: [Second]

        println(queue.isEmpty())  // Check if the queue is empty: false
    }

    fun arrayDequeAsStack() {
        // LIFO
        val stack = ArrayDeque<String>()
        stack.addFirst("TopOfStack") // Push to first position
        stack.addFirst("NewTopOfStack") // Push
        println(stack)
        // Stack is [NewTopOfStack, TopOfStack]
        println(stack.firstOrNull()) // Peek at next person to be pushed off the stack, "NewTopOfStack". (null safety)

        println(stack.removeFirstOrNull()) // Pop top of stack, and returns that element "NewTopOfStack"
        // Given ArrayDeque is mutable list it does it in place.
        // Hence stack is now: [TopOfStack]

        // Susan has just been placed on top of stack
        stack.addFirst("SusanNowTopOFStack") // Push

        println(stack)
        // [SusanNowTopOFStack, TopOfStack]
    }



}
