package Factory

import Chain.CEOHandler
import Chain.ExecutiveHandler
import Chain.Handler
import Chain.ManagerHandler

class EliteFactory: AbstractFactory() {
    override fun getHandler(handler: String): Handler? {
        if(handler=="CEO")
            return CEOHandler();
        if(handler=="Executive")
            return ExecutiveHandler();
        if(handler=="Manager")
            return ManagerHandler();
        else
            println("Unknown Handler!!")
        return null;
    }
}