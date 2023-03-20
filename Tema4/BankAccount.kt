package Cinema
import java.util.Date
class BankAccount(private var availableAmount:Double, private val cardNumber: String, private val cvvCode: Int, private val expirationDate: Date, private val userName:String) {
    fun updateAmount(amount: Double): Boolean {
        if (availableAmount + amount >= 0) {
            availableAmount += amount
            println("Fonduri modificate: $availableAmount")
            return true
        } else {
            println("Nu sunt destule fonduri")
            return false
        }
    }
}