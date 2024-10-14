package reflection

class Cat(private val name: String, private var age: Int) {

    fun getName(): String {
        return name
    }
    fun getAge(): Int {
        return age
    }
    fun setAge(age: Int) {
        this.age = age
    }
    fun meow() {
         println("Meow")
    }

    private fun heyThisIsPrivate() {
        println("HeyThisIsPrivateDudeee")
    }
}