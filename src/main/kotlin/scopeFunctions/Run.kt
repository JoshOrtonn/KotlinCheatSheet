package scopeFunctions

import scopeFunctions.Util.Client

// Configure an object and compute a custom result
class Run {
    fun run() {
        // If instead we wanted to only set the token,
        // And instead of connecting and authenticate upon initialising the client
        // we wanted to instead connect and auth for every request
        // We could use run.


        // Also apply returns the same object,
        // To be used when applying modifications

        val client = Client().apply {
            token = "initializeTokenWithinRun"
        }

        // Whereas run returns the computation of the lambda
        // To be used when running operations
        val result = client.run {
            connect()
            authenticate()
            // Last line would be resulted.
            getData()
        }
    }
}

