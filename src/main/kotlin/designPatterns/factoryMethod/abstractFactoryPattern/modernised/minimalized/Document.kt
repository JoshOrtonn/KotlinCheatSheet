package designPatterns.factoryMethod.abstractFactoryPattern.modernised.minimalized

class ElementFactory(
    val createParagraph: (text: String) -> String,
    val createHeading: (level: Int, text: String) -> String,
    val createLink: (text: String, url: String) -> String
)

val HtmlElementFactory = ElementFactory(
    createParagraph = { text -> "<p>$text</p>" },
    createHeading = { level, text -> "<h$level>$text</h$level>" },
    createLink = { url, text -> "<a href=\"$url\">$text</a>" },
)

val MarkdownElementFactory = ElementFactory(
    createParagraph = { text -> "$text\n" },
    createHeading = { level, text -> "${"#".repeat(level)}$text\n" },
    createLink = { url, text -> "[$text][$url]" },
)

class Document(private val factory: ElementFactory) {
    private val elements = mutableListOf<String>()

    fun addParagraph(text: String) = factory.createParagraph(text)
    fun addHeading(level: Int, text: String) = factory.createHeading(level, text)
    fun addLink(url: String, text: String) = factory.createLink(url, text)
    fun render() = elements.joinToString(separator = "\n")
}

fun main() {
    val htmlDocument = Document(HtmlElementFactory)
    htmlDocument.addParagraph("someParagraph")
    htmlDocument.addHeading(1, "someHeading")
    htmlDocument.addLink("https://Josh.com", "someLink")
    println(htmlDocument.render())


    val markdownDocument = Document(MarkdownElementFactory)
    markdownDocument.addParagraph("someParagraph")
    markdownDocument.addHeading(1, "someHeading")
    markdownDocument.addLink("https://Josh.com", "someLink")
    println(markdownDocument.render())
}