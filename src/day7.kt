import java.io.File

private fun part1(data: List<String>) {
    var output: Long = 0

    val activeBeams: MutableSet<Int> = mutableSetOf()

    data.forEach { line ->
        line.forEachIndexed { i, c ->
            when (c) {
                'S' -> {
                    activeBeams.add(i)
                }
                '^' -> {
                    if (activeBeams.contains(i)) {
                        activeBeams.remove(i)
                        activeBeams.add(i-1)
                        activeBeams.add(i+1)
                        output++
                    }
                }
            }

        }
    }

    println("part 1: $output")
}

private fun part2(data: List<String>) {
    var output: Long = 0

    // key: the column. value: the number of ways to reach that column
    val activeBeams: MutableMap<Int, Int> = mutableMapOf()

    data.forEach { line ->
        line.forEachIndexed { i, c ->
            when (c) {
                'S' -> {
                    activeBeams[i] = 1
                }
                '^' -> {
                    if (activeBeams.contains(i)) {
                        val temp = activeBeams.getOrElse(i) { 0 }
                        activeBeams.remove(i)
                        activeBeams[i-1] = activeBeams.getOrElse(i) { temp } + 1
                        activeBeams[i+1] = activeBeams.getOrElse(i) { temp } + 1
                    }
                }
            }

        }
    }

    output = activeBeams.map { (i,v) -> v }.sum().toLong()

    println("part 2: $output")
}

fun main() {
    val data = File("data/day7.txt").readLines()

    part1(data)
    part2(data)
}