package Chain

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HappyWorkerHandler(override var next: Handler?=null, override var prev: Handler?=null): Handler {
    override suspend fun handleRequest(messageToBeProcessed: String) {
        var prior=Character.getNumericValue(messageToBeProcessed.first())
        if(prior>1)
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
                delay(3000)
                print(messageToBeProcessed);
                println(" Handled by HappyWorker");
                sendResponse("Success!!")
            }
            job.join()
        }
    }

    override fun sendResponse(message: String)
    {
        prev?.sendResponse("HappyWorker msg: $message");
    }
}