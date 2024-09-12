package classes

import java.awt.Color
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement


// Nested class often good example for serizalable models.
// Like this socialMediaPost class, containing a metrics class.
@Serializable
class SocialMediaPost(val id: Int, val text: String, val metrics: Metrics) {
    @Serializable
    class Metrics(val impressions: Int, val clicks: Int, val likes: Int)
}


class NestedClass {
    fun main() {
        // To access nested class, need to construct main class, then access
        // inner nested class
        val params = Canvas.Params(640, 480, Color.GREEN)

        // Example of using JSON decoding for nested serializable classes
        // Especially given they uniquely relate to each other.
        // Below code just highlights purpose of serialization
        val socialMediaPost: SocialMediaPost = Json.decodeFromString(postJSON)
        println("Clicks: ${socialMediaPost.metrics.clicks}")
        val someJsonElement = Json.encodeToJsonElement(socialMediaPost)
        println(someJsonElement.toString())

        val favoriteStrings = FavoriteStrings("Josh", "Lottie", "poop")

        for (favoriteString in favoriteStrings) {
            println(favoriteString)
        }
    }
}

const val postJSON: String = """
{
        "id": 12345,
        "text": "I'm looking forward to watching this movie"
        "metrics": {
            "impressions": 912,
            "clicks": 34,
            "likes": 27
        }
}
"""

class Canvas(params: Params = Params()) {
    val pixels = Array(params.width * params.height) { params.backgroundColour}

    // Good choice to great nested class, when uniquely related to outer class.
    // Or can make it private and still uniquely related, to aid with impl
    // of outer class.
    class Params(
        val width: Int = 1920,
        val height: Int = 1080,
        val backgroundColour: Color = Color.YELLOW
    )
}

// Overkill example of showing how innner class does not have access to outer
// class' variables.
// Writing a new kind of overkill iterator, for specific use case of 1, 2 ,3.

class FavoriteStrings(val first: String, val second: String, val third: String): Iterable<String> {
    override fun iterator(): Iterator<String> = FavoriteStringsIterator()

    // With inner modifier on class it has a reference to the outer classes' state
    // It also then becomes available to instantiated variables of FavouriteStrings
    // Hence private can then reduce the scope of it's availability.
    private inner class FavoriteStringsIterator: Iterator<String> {
        private var currentIndex = 0
        override fun hasNext(): Boolean = currentIndex < 3


        override fun next(): String = when (currentIndex) {
            // Without the inner keyword first, second and third become unavailable
            0 -> first
            1 -> second
            2 -> third
            else -> throw NoSuchElementException()
        }.also {currentIndex++}
    }
}