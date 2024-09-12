package variance.generics.refactored

// This doesn't compile as Mug class is unable to see the new idealTemp property
// As T is not restrictive, we could have Mug<String>
//class Mug<T>(val beverage: T) {
//    val temperature = beverage.idealTemperature
//}

// Hence if we can constrain the upperbounds of the type param so that only Bev types
// Can go inside it.
// Hence T can only ever be Beverage, or one of its subtypes.
//If we don’t specify the upper bound, Kotlin will assume a default of Any?
class Mug<T: Beverage>(val beverage: T){
    val temperature = beverage.idealTemperature
}
// And hence kotlin can access any properties or functions that beverage type includes.


sealed interface Beverage {
    val idealTemperature: Int
}
enum class Coffee: Beverage { LIGHT_ROAST, MEDIUM_ROAST, DARK_ROAST; override val idealTemperature = 140 }
enum class Tea: Beverage { GREEN_TEA, BLACK_TEA, RED_TEA;  override val idealTemperature = 150 }
enum class Beer: Beverage { LAGER, GUINNESS;  override val idealTemperature = 125  }

fun drink(coffee: Coffee) = println("Drinking coffee: $coffee")
fun drink(tea: Tea) = println("Drinking tea: $tea")

// Can use a type param anywhere we normally write a type within the class body.
class Dish2<T>(private var food: T) {
    fun replaceFood(newFood: T) {
        println("Replacing $food with $newFood")
        food = newFood
    }
    fun getFood(): T = food
}

// It's possible for a class to have more than one. E.G combo order for food + bevvy
// Although rare to use more than two or three type parameters in a generic type.
//class ComboOrder<T: Food, U: Beverage>(val food: T, val beverage: U)


interface Dish<T> { val food: T}
class BowlOfSoup(override val food: Soup): Dish<Soup>
//Alternatively, the implementing class can itself
// declare a type parameter, and relay that to the interface, as shown here.
class Bowl<F>(override val food: F): Dish<F>

fun main() {
    val mug = Mug<Coffee>(Coffee.LIGHT_ROAST)
    drink(mug.beverage)
    val mugBeer = Mug<Beer>(Beer.GUINNESS)
//    Given Mug class doesn't have fun to drink, this doesn't work for all bevvies.
//    drink(mugBeer.beverage)

//    val combo = ComboOrder<Pastry, Tea> = ComboOrder(Pastry.MUFFIN, Tea.GREEN_TEA)

    val bowlOfSoup = BowlOfSoup(Soup.TOMATO)

    val bowlOfSoup1: Bowl<Soup> = Bowl(Soup.TOMATO)

    // Tradeoffs with generics
    var mugToBeOverwritten = Mug(Coffee.DARK_ROAST)
    // Type mismatch, whereas previously the mug was of type Mug
    // So could handle any beverage within it.
    // But now, By default, (Beer and Coffee) are parameterized types
    // that are not assignment-compatible.
    // mugToBeOverwritten = mugBeer

    // Type erasure is another significant trade-off
    // Although object's type arguments are known at compile-time
    // They are erased before your code runs
    // I.E an object's type arguments are not known at runtime.
    // Mug<Coffee>
    // Mug is known at compile time and at runtime
    // Coffee is known at compile time, but NOT at runtime

    // Hence:
    val mugWithTypeErasure: Mug<Beverage> = Mug(Coffee.MEDIUM_ROAST)
//    when (mugWithTypeErasure) {
//        // Cannot check for instance of erased type: Mug<Tea>
//        is Mug<Tea>    -> println("Sipping on tea: ${mug.beverage}!")
//        is Mug<Coffee> -> println("Sipping on coffee: ${mug.beverage}!")
//    }

    // But the type of a property that was declared with a type param can.
    when(mugWithTypeErasure.beverage) {
        is Tea -> println("Sipping on tea: ${mugWithTypeErasure.beverage}!")
        is Coffee -> println("Sipping on Coffee: ${mugWithTypeErasure.beverage}!")
        is Beer -> println("Sipping on Beer: ${mugWithTypeErasure.beverage}!")
    }

//    drinkFrom(mugWithTypeErasure)

}

// TODO: Check this:
// Another issue with the fact that Mug<T> where T is unknwon at runtime
// So these two function signatures are the same except for the type argument here
//Platform declaration clash: The following declarations have the same JVM signature
//fun drinkFrom(mug: Mug<Tea>) = println("Drinking tea")
//fun drinkFrom(mug: Mug<Coffee>) = println("Drinking coffee")

enum class Soup {
    TOMATO
}

// Can also create top level functions (outside the class)
// Type param declared between fun and name of function
fun <T: Beverage> serve(beverage: T): Mug<T> = Mug(beverage)
val mug: Mug<Coffee> = serve<Coffee>(Coffee.DARK_ROAST)

// Can even create generic extension functions where reciever type
// is a type parameter
fun <T: Beverage> T.pourIntoMug() = Mug(this)
val mugToBePoured = Coffee.DARK_ROAST.pourIntoMug()