package classes

// Great for storing data, especially immutabble data.
// Automatically get an implementation of equals(), hashcode() and toString()
// Objects are compared by value, instead of by reference
data class Book(
    val id: Int,
    val title: String,
    val author: String
)

// Great for any simple carries of data
// Like to use them for data transfer objects
// Intermediate data types when processing collections
class DataClass {
    fun main() {
        val myBook = Book(256, "The Malt Shop Caper", "Slim Chancery")
        val yourBook = Book(256, "The Malt Shop Caper", "Slim Chancery")

        // Comparision is by value, not refernce, hence true
        println(myBook == yourBook)

        // Unused id can be referenced as _
        // Example of destructured reference / intialization
        // Destructoring by order of appearing in constructor, not by reference.

        val (_, title, author) = myBook

        // Create a copy of the object, with changes you want applied
        val newBook = myBook.copy(id = 1, title = "Josh's Book")

        println("$title written by $author")
    }

}

