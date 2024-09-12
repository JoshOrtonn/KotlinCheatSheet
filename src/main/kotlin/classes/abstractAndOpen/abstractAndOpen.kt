package classes.abstractAndOpen

interface Named {
    val name: String
}

abstract class Car(private val acceleration: Double = 1.0) {
    private var speed = 0.0

    protected open fun makeEngineSound() = println("Vrrrrrrr....")
    fun accelerate() {
        speed += acceleration
        makeEngineSound()
    }
}


// Only one superclass can be extended, but as many interfaces can be
// implemented just seperate with commas.
// Hence why interfaces are more flexible than abstract and open classes
class NamedCar(override val name: String): Car(3.0), Named

fun main() {

}

/*
So, to summarize, abstract classes can be extended by other classes. Their functions and properties can be:

abstract, in which case they have no body in the abstract class, but subclasses must implement them.
open, in which case they have a body in the abstract class, but subclasses may override them.
Final (i.e., neither abstract nor open), in which case subclasses cannot override them.
 */