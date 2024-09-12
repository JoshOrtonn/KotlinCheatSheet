package variance.generics.exampleCode

enum class Coffee { LIGHT_ROAST, MEDIUM_ROAST, DARK_ROAST}
enum class Tea { GREEN_TEA, BLACK_TEA, RED_TEA }

class CoffeeMug(val beverage: Coffee)
class TeaMug(val beverage: Tea)

fun drink(coffee: Coffee) = println("Drinking coffee: $coffee")
fun drink(tea: Tea) = println("Drinking tea: $tea")

fun main() {
    val coffeeMug = CoffeeMug(Coffee.LIGHT_ROAST)
    drink(coffeeMug.beverage)
    val teaMug = TeaMug(Tea.BLACK_TEA)
    drink(teaMug.beverage)
}

