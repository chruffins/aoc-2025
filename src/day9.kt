import java.io.File
import kotlin.math.abs
import kotlin.math.sqrt

private fun area(a: Pair<Long, Long>, b: Pair<Long, Long>): Long {
    return (abs(a.first - b.first) + 1) * (abs(a.second - b.second) + 1)
}

private fun part1(data: List<String>) {
    var output: Long = 0

    val vertices: List<Pair<Long, Long>> = data.map {
        val (a,b) = it.split(",").map {n -> n.toLong()}
        Pair(a, b)
    }

    val edges =
        vertices.flatMapIndexed { i, u ->
            vertices.drop(i + 1).map { v ->
                area(u, v)
            }
        }.sortedBy { it }

    output = edges.max()

    println("part 1: $output")
}

private fun part2(data: List<String>) {
    var output: Long = 0
}

fun main() {
    val data = File("data/day9.txt").readLines()

    part1(data)
    part2(data)
}