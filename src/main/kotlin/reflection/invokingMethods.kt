package reflection

import java.lang.reflect.Method

fun main() {
    val myCat = Cat("Josh", 22)

    val unorderedMethods: Array<Method> = myCat.javaClass.declaredMethods

    unorderedMethods.forEach { it: Method ->
        if(it.name == "meow"){
            it.invoke(myCat)
            // calls method to Meow using reflection
        } else if(it.name == "heyThisIsPrivate"){
            it.trySetAccessible()
            it.invoke(myCat)
            // calls private method using reflection.
        }
    }
}