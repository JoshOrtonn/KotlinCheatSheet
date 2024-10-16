package collectionDataTypes

import java.util.*

class ListConversions {

    fun initializingLists() {
        val list = listOf("#1", "#2", "#3")
        println(list)

        // Or an alternative to this as it's not the most scalable,
        // to add new items to the list is:

        val scalableList = List(3) {
            "#${it+1}"
        }
        // ^^ This then results in the same,
        // and if we change size it'll do the same for 3+ elements
    }

    fun wordleInitializeList() {
        val list = MutableList(5){
            '?'
        }
        list[0] = 'c'
        list[2] = 'a'

        // Creates string from list.
        println(list.joinToString(""))
    }



    fun listJoinToString() {
        val colors = listOf("Red", "Blue", "Orange")

        val newColours = colors.joinToString(separator = " ", prefix = "[", postfix = "]", limit = 2, truncated = "and the bastard rest") {
            it.uppercase()
        }
        println(newColours)

        // To reorder this collection, you could use shuffle() to randomly change order
        val shuffledColours = colors.shuffled()
        val shuffledNewColours = shuffledColours.joinToString(separator = " ", prefix = "[", postfix = "]", limit = 2, truncated = "and the bastard rest") {
            it.uppercase()
        }
        println(shuffledNewColours)

    }

    fun listToMap() {
        val list = listOf("Red", "Blue", "Orange")

        val map = list.map { it.uppercase() to it.length}.toMap()
        println(map)

        // Can be easier read, and more performant using associate
        val associateMap = list.associate { it.uppercase() to it.length }
        println(associateMap)

        // sometimes key can just be the same as string, so no trandformation needed,
        // and implicitally matches each key of list as itself, and value as transformation:
        val mapWithoutKeyTransformation: Map<String, Int> = list.associateWith { it.length }

        println(mapWithoutKeyTransformation)
    }

    fun listToSet() {
        // Remember in a set order does not matter, making it o(1) to getting
        // And can only have unique values, so second blue is ommitted if duplicated.
        val favorites = listOf("red", "green", "blue")
        val theme = listOf("green", "turqouise", "yellow")

        // Returns set joining all items in sets together, ignoring duplicates
        // union is an infix function, allow to be placed between first and second reciever
        // essentially same as: favorites.union(theme)
        println(favorites union theme)
        // so [red, green, blue, turqoise, yellow]


        // anything common between two sets
        println(favorites intersect theme)
        // so [green]

        //And anything in favoruites that doesn't also appear in theme
        // Duplicates are entirely removed essentially.
        println(favorites subtract theme)

        // Remember these infix functions outputs the lists as sets.
    }

    fun uniqueItemsInList() {
        val fruitBasket = listOf("Apple", "Banana", "Orange", "Banana", "ORANGE", "BANANA", "Orange", "Durian")

        val unique = fruitBasket.distinct()

        val uniqueCaseInsensitive = unique.distinctBy {
            it.lowercase()
        }

        println(unique)
        println(uniqueCaseInsensitive)
    }
    fun sortingLists() {
        val students = listOf<Pair<String, Int>>(Pair("Josh", 100), Pair("Lotts", 10), Pair("Susan", 50), Pair("Bob", 30), Pair("Uncle", 10))
        // Sort students by grade descending, then by name ascending
        val sortedStudentList = students.sortedWith(
            compareByDescending<Pair<String, Int>> { it.second}
                .thenBy { it.first }
        )
        //[(Josh, 100), (Susan, 50), (Bob, 30), (Lotts, 10), (Uncle, 10)]

        // Or natual sort order
        val list = listOf<Int>(123, 213,234, 231,12,15).sorted()
        //[12, 15, 123, 213, 231, 234]

        val sortedList = list.sortedDescending()
        // [234, 231, 213, 123, 15, 12]
    }

}
