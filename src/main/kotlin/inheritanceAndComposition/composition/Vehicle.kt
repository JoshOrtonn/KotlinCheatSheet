package inheritanceAndComposition.composition


interface IVehicle {
    val speed: Double
    fun accelerate()
    fun makeEngineSound()
}

class Vehicle(
    private val acceleration: Double
) : IVehicle {
    override var speed = 0.0
    override fun accelerate() {
        speed += acceleration
    }

    override fun makeEngineSound() {
        println("Vrroooom")
    }
}


// With composition no subtype relationship between RaceCar and Vehicle
// + RaceCar has no direct dependency to the implementation of vehicle
// + Massive boost to flexibility for future use cases
// - Had to create an additional IVehicle type (dis)
// - Members manually forwarded so more boilerplate noise.
// - Often easier to use inheritance for simpler examples without need
// - of more types, and duplication.
class RaceCar(private val vehicle: IVehicle): IVehicle {
    // Forwarding the get to speed, and accelerate to vehicle passed in
    override val speed get() = vehicle.speed
    override fun accelerate() = vehicle.accelerate()
    override fun makeEngineSound() {
        println("Vroomy Vroomy")
    }
}

// Consider a future approach whereby the Junker object created
// But does not move, nor make any sound
// With composition we are able to implement new subtypes of IVehicle
// Without having to update all previous existing members if for example
// The car doesn't want to behave like a generic vehicle
// Through use of composition via creating interface of vehicle
// For many other's to update
object Junker : IVehicle {
    override var speed = 0.0
    override fun accelerate() = Unit
    override fun makeEngineSound() = Unit
}



fun main() {
    val raceCar = RaceCar(Vehicle(2.0))
    drive(raceCar)
    println(raceCar.speed)

    val raceCar2 = RaceCar(Junker)
    drive(raceCar2)
}

private fun drive(vehicle: IVehicle) {
    vehicle.makeEngineSound()
    vehicle.accelerate()
    vehicle.accelerate()
}