fun main(args: Array<String>) {
    val numbers = listOf(1, 21, 75, 39, 7, 2, 35, 3, 31, 7, 8)

    val filteredNumbers = numbers.filter { it >= 5 }

    println(filteredNumbers)

    val pairedNumbers = filteredNumbers.zipWithNext()

    println(pairedNumbers)

    val multipliedNumbers = pairedNumbers.map { it.first * it.second }

    println(multipliedNumbers)

    val sum = multipliedNumbers.sum()

    println(sum)


}