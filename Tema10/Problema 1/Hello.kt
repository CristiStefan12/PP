import Chain.CEOHandler
import Chain.ExecutiveHandler
import Chain.HappyWorkerHandler
import Chain.ManagerHandler
import Factory.EliteFactory
import Factory.FactoryProducer
import Factory.HappyWorkerFactory
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    var factoryProd = FactoryProducer()
    var eliteFact: EliteFactory = factoryProd.getFactory("Elite") as EliteFactory
    var workerFact: HappyWorkerFactory = factoryProd.getFactory("Worker") as HappyWorkerFactory

    var CEOH: CEOHandler = eliteFact.getHandler("CEO") as CEOHandler
    var ExecutiveH: ExecutiveHandler = eliteFact.getHandler("Executive") as ExecutiveHandler
    var ManagerH: ManagerHandler = eliteFact.getHandler("Manager") as ManagerHandler
    var HappyWorkerH: HappyWorkerHandler = workerFact.getHandler("Worker") as HappyWorkerHandler

    CEOH.setnext(ExecutiveH);
    ExecutiveH.setprev(CEOH);
    ExecutiveH.setnext(ManagerH);
    ManagerH.setprev(ExecutiveH);
    ManagerH.setnext(HappyWorkerH);
    HappyWorkerH.setprev(ManagerH);

    ManagerH.handleRequest("1")
    CEOH.handleRequest("2")
    ExecutiveH.handleRequest("3")
    CEOH.handleRequest("2")
    HappyWorkerH.handleRequest("4")
}