package classes.abstract

abstract class Car(private val acceleration: Double = 1.0) {
    private var speed = 0.0
    // Private visibility means even own subclasses can't see it, or call it
    // So if we wanted subclasses to access it, we could add the protected visibility modifier
    // This means it's visible to both current class (Car) and it's subclasses (Clunker)
    // But not to clunker instance of the Clunker class
    // And if we wanted the clunker subclass to override the engine sound we should
    // Add the abstract modifier to it.
    // This means all subclasses HAVE to provide an implementation of make enginer sound
    // OR become abstract subclasses themselves. (Essentially passing the buck down)
//    protected abstract fun makeEngineSound()

    // If instead we wanted subclasses to override a function and for Car to provide a
    // default implementation for makeEngineSound(), we can use the open modifier instead
    protected open fun makeEngineSound() = println("Vrrrrrrr....")
    fun accelerate() {
        speed += acceleration
        makeEngineSound()
    }
}

class Clunker(acceleration: Double) : Car(acceleration) {
    override fun makeEngineSound() {
        println("Put-Put-Put")
    }

}
class SimpleCar(acceleration: Double): Car(acceleration)

fun main() {
    val clunker = Clunker(0.25)
    clunker.accelerate()

    val car = SimpleCar(1.2)
    car.accelerate()
}

/*
So, to summarize, abstract classes can be extended by other classes. Their functions and properties can be:

abstract, in which case they have no body in the abstract class, but subclasses must implement them.
open, in which case they have a body in the abstract class, but subclasses may override them.
Final (i.e., neither abstract nor open), in which case subclasses cannot override them.
 */