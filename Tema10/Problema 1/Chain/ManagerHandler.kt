package Chain

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class ManagerHandler(override var next: Handler?=null, override var prev: Handler?=null): Handler {
    override suspend fun handleRequest(messageToBeProcessed: String) {

        var prior=Character.getNumericValue(messageToBeProcessed.first())
        if(prior<2) {
            val job= GlobalScope.launch {
                sendResponse("Send request down")
                next?.handleRequest(messageToBeProcessed);
            }
            job.join()
        }
        else if(prior>2)
        {
            val job= GlobalScope.launch {
                sendResponse("Send request up")
                prev?.handleRequest(messageToBeProcessed);
            }
            job.join()
        }
        else
        {
            val job= GlobalScope.launch {
                delay(2500)
                print(messageToBeProcessed);
                println(" Handled by Manager");
                sendResponse("Success!!")
            }
            job.join()
        }
    }
    override fun sendResponse(message: String)
    {
        prev?.sendResponse("Manager msg: $message");
    }
}