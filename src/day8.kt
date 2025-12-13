import java.io.File
import kotlin.math.sqrt

private fun euclideanDistance(a: Triple<Int, Int, Int>, b: Triple<Int, Int, Int>): Double {
    val dx = (b.first - a.first).toDouble()
    val dy = (b.second - a.second).toDouble()
    val dz = (b.third - a.third).toDouble()
    return sqrt(dx * dx + dy * dy + dz * dz)
}

private fun part1(data: List<String>) {
    var output: Long = 0

    val vertices: List<Triple<Int, Int, Int>> = data.map {
        val (a,b,c) = it.split(",").map {n -> n.toInt()}
        Triple(a, b, c)
    }

    val edges =
        vertices.flatMapIndexed { i, u ->
            vertices.drop(i + 1).map { v ->
                Triple(u, v, euclideanDistance(u, v))
            }
        }.sortedBy { it.third }

    val circuits: MutableList<MutableList<Triple<Int, Int, Int>>> = mutableListOf()

    edges.take(1000).forEach { edge ->

        val circuit1 = circuits.firstOrNull { it.contains(edge.first) }
        val circuit2 = circuits.firstOrNull { it.contains(edge.second) }

        // case 1: both endpoints already belong to circuits
        if (circuit1 != null && circuit2 != null) {

            // if they are the same circuit, nothing to do
            if (circuit1 !== circuit2) {
                circuits.remove(circuit1)
                circuits.remove(circuit2)

                circuits.add(
                    (circuit1 + circuit2).distinct().toMutableList()
                )
            }

            // case 2: only first endpoint is in a circuit
        } else if (circuit1 != null) {
            circuit1.add(edge.second)

            // case 3: only second endpoint is in a circuit
        } else if (circuit2 != null) {
            circuit2.add(edge.first)

            // case 4: neither endpoint is in any circuit
        } else {
            circuits.add(mutableListOf(edge.first, edge.second))
        }
    }

    val sortedCircuits = circuits.sortedByDescending { it.size }.take(3)
    output = sortedCircuits.fold(1) {
        acc, n -> acc * n.size
    }

    println(sortedCircuits)

    println("part 1: $output")
}

private fun part2(data: List<String>) {
    var output: Long = 0

    val vertices: List<Triple<Int, Int, Int>> = data.map {
        val (a,b,c) = it.split(",").map {n -> n.toInt()}
        Triple(a, b, c)
    }

    val edges =
        vertices.flatMapIndexed { i, u ->
            vertices.drop(i + 1).map { v ->
                Triple(u, v, euclideanDistance(u, v))
            }
        }.sortedBy { it.third }

    val circuits: MutableList<MutableList<Triple<Int, Int, Int>>> = mutableListOf()

    var lastTwoLength: Long = 0

    edges.forEach { edge ->

        val circuit1 = circuits.firstOrNull { it.contains(edge.first) }
        val circuit2 = circuits.firstOrNull { it.contains(edge.second) }

        // case 1: both endpoints already belong to circuits
        if (circuit1 != null && circuit2 != null) {

            // if they are the same circuit, nothing to do
            if (circuit1 !== circuit2) {
                circuits.remove(circuit1) 
                circuits.remove(circuit2)

                circuits.add(
                    (circuit1 + circuit2).distinct().toMutableList()
                )

                if (lastTwoLength == 0L && circuits.size == 1) {
                    lastTwoLength = (edge.first.first * edge.second.first).toLong()
                }
            }

            // case 2: only first endpoint is in a circuit
        } else if (circuit1 != null) {
            circuit1.add(edge.second)

            // case 3: only second endpoint is in a circuit
        } else if (circuit2 != null) {
            circuit2.add(edge.first)

            // case 4: neither endpoint is in any circuit
        } else {
            circuits.add(mutableListOf(edge.first, edge.second))
        }
    }

    println("part 2: $lastTwoLength")
    println(circuits.first().size)
}

fun main() {
    val data = File("data/day8.txt").readLines()

    part1(data)
    part2(data)
}