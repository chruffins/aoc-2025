import java.io.File

private val datafn: String = "data/day2.txt"

private fun part1() {
    val data = File(datafn).readLines()[0]
    val ranges: List<String> = data.split(",")

    var counter: Long = 0
    ranges.forEach {
        val (first, last) = it.split("-").map { s ->
            s.toLong()
        }

        for (i in first..last) {
            val t = i.toString()

            if (t.length % 2 == 0) {
                val l = t.length / 2
                if (t.take(l) == t.drop(l)) counter += i
            }
        }
    }

    // too much thought?
    /*
    ranges.forEach {
        val split = it.split("-")
        val lowString = split[0]
        val highString = split[1]

        val actualRange = lowString.toLong() .. highString.toLong()

        // we need to know how to determine where to find invalid ids
        // over the range of values, actually loop over the first half of digits
        // iff number of digits in the number is odd, then skip?

        val lowHalf = lowString.take(ceil(lowString.length / 2.0f).toInt())
        val highHalf = highString.take(ceil(highString.length / 2.0f).toInt())

        for (i in (lowHalf.toInt() .. highHalf.toInt())) {
            val calced = (i.toString() + i.toString()).toLong()
            if (calced in actualRange) {
                counter += calced
                println("$it: $calced")
            }
        }
    }
    */

    println("invalids = $counter")
}

private fun part2() {
    val data = File(datafn).readLines()[0]
    val ranges: List<Pair<String, String>> = data.split(",").map {
        val (first, last) = it.split("-")

        first to last
    }

    val regex = Regex("^(\\d+)\\1+")
    var counter: Long = 0

    ranges.forEach { (first, last) ->
        for (i in first.toLong()..last.toLong()) {
            val s = i.toString()

            if (regex.matches(s)) {
                counter += i
            }
        }
    }

    println("invalids (pt 2) = $counter")
}

fun main() {
    part1()
    part2()
}