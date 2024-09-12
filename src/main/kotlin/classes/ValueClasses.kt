package classes


// Classes often used to wrap a simple type to a
// more strongly typed domain model.
// However by introducing a class, for number of books
// it will instatiate a lot of kotlin.classes,
// meaning a lot of space on the heap
//class Id(val value: Int)
//class Title(val value: String)
//class Author(val value: String)

// Hence we can use an @JVMInline value class instead
// Which essentially means kotlin can trat each of them
// as their underlying primitive types, improving performance.
// Whilst keep type safety, has to be only a single read only type.
// JVM goes on the stack, not the heap.
@JvmInline value class Id(val value: Int)
@JvmInline value class Title(val value: String)
@JvmInline value class Author(val value: String)

data class Booky(
    val id: Id,
    val title: Title,
    val author: Author
)

class ValueClasses {

    fun createBook(): Booky {
        val id = Id(1)
        val title = Title("Title")
        val author = Author("Author")

        // By using kotlin.classes instead of primitive types
        // It's harder to mess up order, or go wrong.
        val myBook = Booky(id, title, author)

        return myBook

    }
}