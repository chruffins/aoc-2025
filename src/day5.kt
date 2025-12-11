import java.io.File

private fun part1(data: List<String>) {
    var output: Long

    val delimiter = data.indexOfFirst { it == "" }
    val ranges = data.take(delimiter).map {
        val (first, last) = it.split("-")

        first.toLong() to last.toLong()
    }
    val ids = data.drop(delimiter + 1).map { it.toLong() }

    output = ids.filter { id ->
        ranges.any { (first, last) ->
            id in first..last
        }
    }.size.toLong()

    println("part 1: $output")
}

private fun part2(data: List<String>) {
    var output: Long = 0

    val delimiter = data.indexOfFirst { it == "" }
    val ranges = data.take(delimiter).map {
        val (first, last) = it.split("-")

        first.toLong() to last.toLong()
    }.sortedBy { (first, _) -> first }

    val mergedRanges: MutableList<Pair<Long, Long>> = mutableListOf(ranges.first())

    ranges.forEach { (first, last) ->
        val mergedLastLast = mergedRanges.last().second

        if (first <= mergedLastLast) {
            mergedRanges[mergedRanges.size - 1] = Pair(mergedRanges[mergedRanges.size - 1].first,
                maxOf(mergedLastLast, last))
        } else {
            mergedRanges.add(Pair(first, last))
        }
    }

    for (pair in mergedRanges) {
        output += (pair.second + 1 - pair.first)
    }

    println("part 2: $output")
}

fun main() {
    val data = File("data/day5.txt").readLines()

    part1(data)
    part2(data)
}