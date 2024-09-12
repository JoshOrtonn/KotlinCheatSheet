package delegatedProperties.delegationExample

// As you can see delegation provides a lot of boilerplate, when these delegated jobs expands
interface KitchenStaff {
    fun prepareEntree(name: String): Entree?
    fun prepareAppetizer(name: String): Appetizer?
    fun prepareDessert(name: String): Dessert?
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

class Waiter(private val chef: Chef): KitchenStaff {
    fun prepareBeverage(name: String): Beverage? =
        when(name){
            "Water" -> Beverage.WATER
            "Soda" -> Beverage.SODA
            else -> null
        }

    fun acceptPayment(money: Int) = println("Thank you for paying for your meal!")
    // As you can see delegation provides a lot of boilerplate, when these delegated jobs expands
    override fun prepareEntree(name: String): Entree? = chef.prepareEntree(name)
    override fun prepareAppetizer(name: String): Appetizer? = chef.prepareAppetizer(name)
    override fun prepareDessert(name: String): Dessert? = chef.prepareDessert(name)
    override fun recieveCompliment(message: String) = chef.recieveCompliment(message)
}
