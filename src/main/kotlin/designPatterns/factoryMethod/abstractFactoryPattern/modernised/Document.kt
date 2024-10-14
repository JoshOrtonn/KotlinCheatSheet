package designPatterns.factoryMethod.abstractFactoryPattern.modernised

/*

    Lightweight Abstract Factory Pattern, modernized.

 */


// Product
interface Element {
    // property that has a function type.
    val render: () -> String
}
// Product
class Paragraph(override val render: () -> String) : Element {}


class Heading(private val level: Int, private val doRender: (level: Int) -> String) : Element {
    init { require(level in 1..6) { "Heading level must be between 1 and 6" } }
    override val render = { doRender(level) }
}

//Abstract Product
class Link(override val render: () -> String) : Element


// Client
// If we ever need to introduce a new format, the document class can remain completly untouched
// This is because we've seperated the concerns of formatting
// from the concerns of editing te document, so it's more resilient.
class Document(private val factory: ElementFactory) {
    private val elements = mutableListOf<Element>()
    fun addParagraph(paragraph: String) = elements.add(factory.createParagraph(paragraph))
    fun addHeading(level: Int, text: String) = elements.add(factory.createHeading(level, text))
    fun addLink(uri: String) = elements.add(factory.createLink(uri))

    // Lambda is called based on transform argument within joinToString.
    fun render() = elements.joinToString("\n") { it.render() }
}

// Abstract Factory
interface ElementFactory {
    fun createParagraph(text: String): Paragraph
    fun createHeading(level: Int, text: String): Heading
    fun createLink(uri: String): Link
}

// Implementations of abstract factory is called concrete factories
object HtmlElementFactory: ElementFactory {
    override fun createParagraph(text: String) = Paragraph { "<p>$text</p>" }
    override fun createHeading(level: Int, text: String) = Heading(level)  { "<h$level> $text </h$level>" }
    override fun createLink(uri: String): Link = Link { "<a href=\"$uri\">$uri</a>" }

}
object MarkdownElementFactory: ElementFactory {
    override fun createParagraph(text: String) = Paragraph { "$text\n" }
    override fun createHeading(level: Int, text: String) = Heading(level) { "${"#".repeat(level)}$text" }
    override fun createLink(uri: String): Link = Link { "<a href=\"$uri\">$uri</a>" }
}

fun main() {
    val markdownDocument = Document(MarkdownElementFactory)
    markdownDocument.addHeading(2, "some heading markdown")
    markdownDocument.addParagraph("some markdown paragraph")
    markdownDocument.addLink("someURI.com")
    println(markdownDocument.render())

    val htmlDocument = Document(HtmlElementFactory)
    htmlDocument.addHeading(2, "some heading html")
    htmlDocument.addParagraph("some html paragraph")
    htmlDocument.addLink("someURI.com")
    println(htmlDocument.render())
}