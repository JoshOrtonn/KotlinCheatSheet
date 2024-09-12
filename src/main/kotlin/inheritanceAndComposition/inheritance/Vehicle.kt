package inheritanceAndComposition.inheritance

import inheritanceAndComposition.composition.RaceCar

open class Vehicle(
    private val acceleration: Double
) {
    var speed = 0.0
    fun accelerate() {
        speed += acceleration
    }

    open fun makeEngineSound() {
        println("Vrroooom")
    }
}

// Relationship is static, as is declared here, upon which every raceCar must
// IMplement vehicle's methods, hence not as flexible if vehicle was to change
// All subtypes would have to change
// but is consice and clear representation of relationship
class RaceCar(acceleration: Double) : Vehicle(acceleration) {
    override fun makeEngineSound() {
        println("Vroomy Vroomy")
    }
}

fun main() {
    val raceCar = RaceCar(2.0)
    drive(raceCar)
    println(raceCar.speed)
}

private fun drive(vehicle: Vehicle) {
    vehicle.makeEngineSound()
    vehicle.accelerate()
    vehicle.accelerate()
}