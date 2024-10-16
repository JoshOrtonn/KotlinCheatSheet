package builderFunctions

class CollectionBuilder {
    fun buildList(): List<Int> {
        val myFavouriteNumbers = listOf(42, 815, 7, 2016)
        //Immuttable
        val myList = buildList {
            add(1)
            addAll(myFavouriteNumbers)
        }
        //    [1, 42, 815, 7, 2016]
        return myList
    }

    fun buildMap(): Map<String, Int> {
        val medalTallySet = buildMap {
            put("Gold", 12)
            put("Silver", 4)
            put("Bronze", 2)
        }
        // Immutable map once created

        // prizes["Gold"] = 6

        return medalTallySet
    }

    fun buildSet(): Set<String> {
        val medalOptions = buildSet {
            add("Gold")
            add("Silver")
            add("Bronze")
        }

        return medalOptions
    }
}