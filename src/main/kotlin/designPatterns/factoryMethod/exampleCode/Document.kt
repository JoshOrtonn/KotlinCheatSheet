package designPatterns.factoryMethod.exampleCode

class Document(private val format: Format) {
    private val elements = mutableListOf<String>()

    // If only dealing with one format, maybe this is fine
    // But as more and more formats come up, gonna explode into more lines
    // Easier to extract based on FormatType to different classes.
    fun addParagraph(text: String) {
        val paragraph = when(format) {
            Format.HTML -> "<p>$text</p>"
            Format.MARKDOWN -> "$text\n"
        }
        elements.add(paragraph)
    }

    enum class Format {HTML, MARKDOWN}

    fun render() = elements.joinToString("\n")

}

fun main() {
    val doc = Document(Document.Format.MARKDOWN)
    doc.addParagraph("Hello World!")
    println(doc.render())
}