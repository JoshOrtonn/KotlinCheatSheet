package designPatterns

import designPatterns.strategyPattern.kotlinModernized.Validator

// Strategy pattern

// Strategy
private interface Validator {
    fun isValid(value: String): Boolean
}

// 3 classes whcih implement them known as concrete strategy
private class EmailValidator : Validator {
    override fun isValid(value: String): Boolean {
        return value.contains("@") && value.contains(".")
    }
}
private class UsernameValidator : Validator {
    override fun isValid(value: String): Boolean {
        return value.isNotEmpty()
    }
}
private class PasswordValidator : Validator {
    override fun isValid(value: String): Boolean {
        return value.length >= 8
    }
}


// Known as the context
private class FormField(val name: String, val value: String, private val validator: Validator) {
    fun isValid(): Boolean {
        return validator.isValid(value)
    }
}