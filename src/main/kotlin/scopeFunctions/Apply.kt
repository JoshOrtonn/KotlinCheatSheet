package scopeFunctions

import scopeFunctions.Util.Client

// Configure objects in a builder type API
class Apply {
    fun apply() {
        val client = Client()
        /*
        Do bunch of stuff initialising
        Then finally set token, connect and authenticate hundred lines below
         */

        // So instead we can use builder style using .apply()
        // To group initialization of the object

        client.apply {
            token = "apply"
            connect()
            authenticate()
        }

        println(client.getData())

    }
}