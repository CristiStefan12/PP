package Cinema
import java.util.*
import java.util.Date

fun main(args: Array<String>) {

    var ticketFee : Double = 15.00

    val cashPayment = CashPayment(20.0)
    if (cashPayment.pay(ticketFee)) {
        println("Bilet emis")
    } else {
        println("Nu s-a putut plati")
    }
    val cardPayment = CardPayment(BankAccount(500.00, "500 222 333 444 555", 123, Date(2028,12,23),"Marian Valentin"))
    if(cardPayment.pay(ticketFee))
    {
        println("Bilet emis")
    } else {
        println("Nu s-a putut plati")
    }


}