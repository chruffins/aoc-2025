import java.io.File

private fun part1(data: List<String>) {
    var output: Long = 0

    val parts = data.dropLast(1).map { it.split(Regex("\\s+")).map { num -> num.toLong() } }
    val tParts = List(parts.first().size) { cols ->
        List(parts.size) { rows ->
            parts[rows][cols]
        }
    }
    val ops = data.last().split(Regex("\\s+"))

    ops.forEachIndexed { i, op ->
        val nums = tParts[i]
        when (op) {
            "+" -> {
                output += nums.reduce { run, n -> run + n}
            }
            "*" -> {
                output += nums.reduce { run, n -> run * n}
            }
        }
    }

    println("part 1: $output")
}

private fun part2(data: List<String>) {
    var output: Long = 0

    val parts = data.dropLast(1).map { it.split(Regex("\\s+")).map { num -> num.reversed().toLong() } }
    val tParts = List(parts.first().size) { cols ->
        List(parts.size) { rows ->
            parts[rows][cols]
        }
    }
    val ops = data.last().split(Regex("\\s+"))

    ops.forEachIndexed { i, op ->
        val nums = tParts[i]
        when (op) {
            "+" -> {
                output += nums.reduce { run, n -> run + n}
            }
            "*" -> {
                output += nums.reduce { run, n -> run * n}
            }
        }
    }

    println("part 1: $output")
}

fun main() {
    val data = File("data/day6.txt").readLines()

    part1(data)
    part2(data)
}