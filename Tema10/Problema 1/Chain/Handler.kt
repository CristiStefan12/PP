package Chain
interface Handler {
    var next: Handler?
    var prev: Handler?

    suspend fun handleRequest(messageToBeProcessed: String)
    fun sendResponse(message: String)

    fun setnext(handler: Handler) {
        this.next = handler;
    }
    fun setprev(handler: Handler) {
        this.prev = handler;
    }
}