package scopeFunctions

// Execute a block only on non-null objects
class Let {

    fun sendNotification(recipentAddress: String): String {
        println("Yo $recipentAddress!")
        return recipentAddress
    }

    fun getNextAddress(): String? {
        return "josh@jetbrains.com"
    }

    fun let() {
        val addr: String? = getNextAddress()
        // Note the addr is nullable, so need to null check it
        // Or throw exception which is really bad...

        //  val addr: String = getNextAddress() ?: error("Oh no!")

        val confirmVerbose = if (addr != null) {
            sendNotification(addr)
        } else {
            null
        }

        // Exact same as code snippet above.
        val confirm = addr?.let {
            sendNotification(it)
        }


    }

}