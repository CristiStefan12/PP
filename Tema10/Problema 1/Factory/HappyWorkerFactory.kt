package Factory

import Chain.Handler
import Chain.HappyWorkerHandler

class HappyWorkerFactory: AbstractFactory() {
    override fun getHandler(handler: String): Handler? {
        if(handler=="Worker")
            return HappyWorkerHandler();
        else
            println("Unknown type!!")
        return null;
    }
}