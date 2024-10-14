package designPatterns.factoryMethod.abstractFactoryPattern

/*

    Abstract Factory Pattern

 */


// Product
interface Element {
    fun render(): String
}
// Product
abstract class Paragraph(protected val text: String) : Element

// To add a new element, Heading it required adding a new abstract Product
// Then concrete products for both Html and Markdown headers
// Update creator and concrete creator.
// To really see this in action, lets add a new element type, a link
abstract class Heading(protected val level: Int, protected val text: String) : Element {
    init { require(level in 1..6) { "Heading level must be between 1 and 6" } }
}

//Abstract Product
abstract class Link(protected val uri: String) : Element


// Concrete Products
class HtmlParagraph(text: String) : Paragraph(text) {
    override fun render() = "<p>$text</p>"
}
class HtmlHeading(level: Int, text: String) : Heading(level, text) {
    override fun render() = "<h$level>$text</h$level>"
}
class HtmlLink(uri: String) : Link(uri) {
    override fun render(): String = "<a href=\"$uri\">$uri</a>"
}
class MarkdownParagraph(text: String) : Paragraph(text) {
    override fun render() = "$text\n"
}
class MarkDownHeading(level: Int, text: String) : Heading(level, text) {
    override fun render() = "${"#".repeat(level)} $text\n"
}
class MarkDownLink(text: String) : Link(text) {
    override fun render(): String = "<a href=\"$uri\">$uri</a>"
}

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
class HtmlElementFactory: ElementFactory {
    override fun createParagraph(text: String): HtmlParagraph = HtmlParagraph(text)
    override fun createHeading(level: Int, text: String): HtmlHeading = HtmlHeading(level, text)
    override fun createLink(uri: String): Link = HtmlLink(uri)

}
class MarkdownElementFactory: ElementFactory {
    override fun createParagraph(text: String): MarkdownParagraph = MarkdownParagraph(text)
    override fun createHeading(level: Int, text: String): Heading = MarkDownHeading(level, text)
    override fun createLink(uri: String): Link = MarkDownLink(uri)
}

fun main() {
    val markdownDocument = Document(MarkdownElementFactory())
    markdownDocument.addHeading(2, "some heading markdown")
    markdownDocument.addParagraph("some markdown paragraph")
    markdownDocument.addLink("someURI.com")
    println(markdownDocument.render())

    val htmlDocument = Document(HtmlElementFactory())
    htmlDocument.addHeading(2, "some heading html")
    htmlDocument.addParagraph("some html paragraph")
    htmlDocument.addLink("someURI.com")
    println(htmlDocument.render())
}