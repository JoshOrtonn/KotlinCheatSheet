package referenceEquality

class ChildClass(val name: String)
data class ChildDataClass(val name: String)


class ReferenceEquality {
    fun isClassInstancesEqual(): Boolean {
        val fiona1 = ChildClass("Fiona")
        val fiona2 = ChildClass("Fiona")

        return fiona1 == fiona2
        // false because they are two different child objects
    }

    fun isClassDataClassesEqual(): Boolean {
        val fiona1 = ChildDataClass("Fiona")
        val fiona2 = ChildDataClass("Fiona")

        return fiona1 == fiona2 // true
    }
}

fun main() {
    val referenceEquality = ReferenceEquality()

    println(referenceEquality.isClassInstancesEqual())
    println(referenceEquality.isClassDataClassesEqual())
}