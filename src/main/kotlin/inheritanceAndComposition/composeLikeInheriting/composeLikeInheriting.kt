package inheritanceAndComposition.composeLikeInheriting

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

// By delegate keyword, means kotlin will delegate to passed in vehicle
// auto-forward to the calls to vehicle object for us
// Unless we specifically override it.
// Only delegates from constructor,
// so cannot reassign raceCar and forwarding will not work
// race.vehicle = Junker
class RaceCar(private val vehicle: IVehicle): IVehicle by vehicle {
    override fun makeEngineSound() {
        println("Vroomy Vroomy")
    }
}

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