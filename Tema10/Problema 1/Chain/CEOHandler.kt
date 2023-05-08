package Chain

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CEOHandler(override var next: Handler?=null, override var prev: Handler?=null) : Handler {

    override suspend fun handleRequest(messageToBeProcessed: String) {

        var prior=Character.getNumericValue(messageToBeProcessed.first())
        if(prior<4) {
            val job= GlobalScope.launch {
                sendResponse("Send request down")
                next?.handleRequest(messageToBeProcessed);
            }
            job.join()
        }
        else
        {
            val job= GlobalScope.launch {
                delay(1000)
                print(messageToBeProcessed);
                println("Handled by CEO");
                sendResponse("Success!!")
            }
            job.join()
        }
    }

    override fun sendResponse(message: String)
    {
        println("CEO msg: $message");
    }
}