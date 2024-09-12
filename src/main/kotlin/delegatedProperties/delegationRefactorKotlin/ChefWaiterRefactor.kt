package delegatedProperties.delegationRefactorKotlin


// As you can see delegation provides a lot of boilerplate, when these delegated jobs expands
interface KitchenStaff {
    fun prepareEntree(name: String): Entree?
    fun prepareAppetizer(name: String): Appetizer?
    fun prepareDessert(name: String): Dessert?
    fun recieveCompliment(message: String)
}
interface BarStaff {
    fun prepareBeverage(name: String): Beverage?
    fun recieveCompliment(message: String)
}

interface MenuItem
enum class Entree: MenuItem {
    TOSSED_SALAD,
    SALMON_ON_RICE
}
class Appetizer: MenuItem
class Dessert: MenuItem
class Main: MenuItem

enum class Beverage: MenuItem {
    WATER,
    SODA
}


class Chef: KitchenStaff {
    override fun prepareEntree(name: String): Entree? = when(name) {
        "Tossed Salad" -> Entree.TOSSED_SALAD
        "Salmon on Rice" -> Entree.SALMON_ON_RICE
        else -> null
    }

    override fun prepareAppetizer(name: String): Appetizer? = TODO("Not yet implemented")
    override fun prepareDessert(name: String): Dessert? = TODO("Not yet implemented")
    override fun recieveCompliment(message: String): Unit = TODO("Not yet implemented")
}

class Bartender: BarStaff {
    override fun prepareBeverage(name: String): Beverage? =
        when(name){
            "Water" -> Beverage.WATER
            "Soda" -> Beverage.SODA
            else -> null
        }

    override fun recieveCompliment(message: String) {
        println("Thanks")
    }
}

// By keyword can remove the manual delegation and it will by default pass to the chef
// Same way with BarStaff
class Waiter(private val chef: Chef, private val bartender: Bartender): KitchenStaff by chef, BarStaff by bartender {
    fun acceptPayment(money: Int) = println("Thank you for paying for your meal!")

    // If for instance the kitchen is getting busy, we can still provide an override for the kitchen staff
    // And instead delegate for the other functions, but only override this one.
    override fun prepareEntree(name: String): Entree? =
        if (name == "Tossed Salad") {
            Entree.TOSSED_SALAD
        } else chef.prepareEntree(name)

    // If we share a common function we need to decide which delegate to send to
    // As otherwise we will get an error
    // Hence can do something like this
    override fun recieveCompliment(message: String) = when {
        message.contains("entree")   -> chef.recieveCompliment(message)
        message.contains("beverage") -> bartender.recieveCompliment(message)
        else                         -> println("Waiter received compliment: $message")
    }

}
