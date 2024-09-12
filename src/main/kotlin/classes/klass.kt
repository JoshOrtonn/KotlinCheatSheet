package classes

import kotlin.math.PI

// Example of multiple classes in a single file,
// Encapsulating state with operations that act
// on it.
class Circle(var radius: Double) {
    fun circumference() = 2 * PI * radius
}

class Square(val size: Double)