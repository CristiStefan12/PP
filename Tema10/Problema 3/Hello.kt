import kotlinx.coroutines.*
import java.util.*

fun main() {
    runBlocking {
        val queue = LinkedList(listOf(10, 20, 30, 40))

        val job1 = launch { calculateSum(queue) }
        val job2 = launch { calculateSum(queue) }
        val job3 = launch { calculateSum(queue) }
        val job4 = launch { calculateSum(queue) }

        job1.join()
        job2.join()
        job3.join()
        job4.join()
    }
}

suspend fun calculateSum(queue: Queue<Int>) {
    while (true) {
        val n = queue.poll() ?: break

        var sum = 0
        for (i in 0..n) {
            sum += i
        }
        println("Suma de la 0 la $n este $sum")
    }
}
