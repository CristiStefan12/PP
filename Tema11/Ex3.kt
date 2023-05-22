fun main() {
    val n = readLine()?.toIntOrNull() ?: return
    val points = mutableListOf<Pair<Int, Int>>()

    repeat(n) {
        val (x, y) = readLine()?.split(" ")?.map { it.toIntOrNull() } ?: return
        points.add((x to y) as Pair<Int, Int>)
    }

    val perimeter = points.zipWithNext { p1, p2 ->
        val (x1, y1) = p1
        val (x2, y2) = p2
        Math.abs(x2 - x1) + Math.abs(y2 - y1)
    }.sum()

    println(perimeter)
}
