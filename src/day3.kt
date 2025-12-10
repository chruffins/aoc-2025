import java.io.File

private fun part1(data: List<String>) {
    var output: Long = 0

    data.forEach { bank ->
        // find the highest digit and the next highest digits before and after
        val highest = bank.max()
        val highestIndex = bank.indexOfFirst { it == highest }
        val afterHighest = bank
            .drop(highestIndex + 1)
            .maxOrNull()
        val beforeHighest = bank
            .dropLast(bank.length - highestIndex)
            .maxOrNull()

        //println(bank.dropLast(bank.length - highestIndex) + " $highest " + bank.drop(highestIndex + 1))

        if (afterHighest == null) {
            output += (beforeHighest.toString() + highest.toString()).toInt()
        } else if (beforeHighest == null) {
            output += (highest.toString() + afterHighest.toString()).toInt()
        } else {
            output += (highest.toString() + afterHighest.toString()).toInt()
        }
    }

    println("part 1: $output")
}

private fun part2(data: List<String>) {
    fun perm(s: String, size: Int = 12): String {
        if (size == 0) {
            return ""
        }

        val max = s.dropLast(size - 1).max()
        return max + perm(s.substring(s.indexOf(max) + 1), size - 1)
    }
    println(data.sumOf {
        perm(it).toLong()
    })
}

fun main() {
    val data = File("data/day3.txt").readLines()

    part1(data)
    part2(data)
}