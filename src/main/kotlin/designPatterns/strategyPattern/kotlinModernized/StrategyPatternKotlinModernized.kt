package designPatterns.strategyPattern.kotlinModernized
// Strategy pattern

// Strategy
// As the interface contains a single abstract method (SAM)
// We can create a lambda like below instead
// Just ensure you add the fun keyword to the interface
fun interface Validator {
    fun isValid(value: String): Boolean
}

val emailValidator = Validator { it.contains("@") && it.contains(".") }
val usernameValidator = Validator { it.isNotEmpty() }
val passwordValidator = Validator { it.length >= 8 }

// Known as the context
class FormField(val name: String, val value: String, private val validator: Validator) {
    fun isValid(): Boolean {
        return validator.isValid(value)
    }
}