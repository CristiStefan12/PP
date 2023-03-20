package Cinema
interface PaymentMethod {
    fun pay(fee: Double): Boolean
}

class CashPayment(private val cashAmount: Double) : PaymentMethod {
    override fun pay(fee: Double): Boolean {
        if (cashAmount >= fee) {
            println("Am platit $fee lei cu cash")
            return true
        } else {
            println("Nu sunt destui cash ca sa platesti $fee")
            return false
        }
    }
}