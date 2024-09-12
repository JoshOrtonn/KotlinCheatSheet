package classes.open

/*
An open class is a class that can both be extended and instantiated directly
But it cannot contain any abstract functions, because, what would happen when called?
 */
open class Car(private val acceleration: Double = 1.0){
    // Has to be protected, because if it's private the subclass MuscleCar
    // will not be visible to compare, although it makes it such that
    // speed can be updated in subclqsses, outside accelerate
    protected var speed = 0.0
        private set
    // Makes visibility of speed property protected on its getter
    // And private on it's setter
    protected open fun makeEngineSound() = println("Vrrrrrrr....")

    fun accelerate() {
        speed += acceleration
        makeEngineSound()
    }
}

class MuscleCar : Car(5.0) {
    override fun makeEngineSound() = when {
        speed < 10.0 -> println("Vrooooom")
        speed < 20.0 -> println("Vrooooooooom")
        else         -> println("Vrooooooooooooooooooom!")
    }
}

class Clunker(acceleration: Double) : Car(acceleration) {
    override fun makeEngineSound() {
        println("putt-putt-putt")
//        speed = 999.0 // Yikes! Shouldn't be able to increase the
        // speed without calling accelerate()!
    }
}


fun main() {
    val myCar = Car()
}