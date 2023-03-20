package Cinema
class CardPayment( private val bankAccount: BankAccount) : PaymentMethod {
    override fun pay(fee: Double): Boolean {
        val isSuccess = bankAccount.updateAmount(-fee)
        if (isSuccess) {
            println("Am platit $fee cu cardul.")
        } else {
            println("Plata nu a reusit")
        }
        return isSuccess
    }
}

