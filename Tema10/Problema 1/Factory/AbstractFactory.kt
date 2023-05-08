package Factory

import Chain.Handler

abstract class AbstractFactory {
    abstract fun getHandler(handler: String): Handler?
}