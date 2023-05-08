package Factory

class FactoryProducer {
    fun getFactory(choice: String): AbstractFactory? {
        if(choice=="Elite")
            return EliteFactory();
        if(choice=="Worker")
            return HappyWorkerFactory();
        else
            println("Unknown type!!")
        return null;
    }
}