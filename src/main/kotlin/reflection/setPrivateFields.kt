package reflection

import kotlin.reflect.KProperty
import kotlin.reflect.full.memberProperties

class setPrivateFields {
    fun main() {
        val cat = Cat("Josh", 22)
        println(cat.getName())
        // Josh

        // But it's possible to set private fields using reflection
        val catFields = cat.javaClass.declaredFields
        // Note it's possible to use the kotlin notation of ::class but, it only works for vars, not vals.
        // Hence using .javaClass can allow for use of private field changing of vars.
        val catFieldsKotlin = cat::class.members.find { it.name == "name" } as KProperty<*>
        catFields.forEach {
            if(it.name == "name") {
                it.trySetAccessible()
                it.set(cat, "Josh Orton")
            }
        }

        println(cat.getName())
        // Josh Orton
    }
}
