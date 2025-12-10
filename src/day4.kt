import java.io.File

private fun part1(data: List<String>) {
    var output: Long = 0

    data.forEachIndexed { lineNum, line ->
        // check the adjacent characters
        line.forEachIndexed { pos, char ->
            if (char == '@') {
                var sum = 0
                val aboveLine = data.getOrNull(lineNum - 1)
                val left = line.getOrNull(pos - 1)
                val right = line.getOrNull(pos + 1)
                val bottomLine = data.getOrNull(lineNum + 1)

                sum = listOf(
                    aboveLine?.getOrNull(pos - 1),
                    aboveLine?.getOrNull(pos),
                    aboveLine?.getOrNull(pos + 1),
                    left,
                    right,
                    bottomLine?.getOrNull(pos - 1),
                    bottomLine?.getOrNull(pos),
                    bottomLine?.getOrNull(pos + 1),
                ).filter { it == '@' }
                    .size

                if (sum < 4) output++
            }
        }
    }

    println("part 1: $output")
}

private fun part2(data: List<String>) {
    var output: Long = 0
    var found = 1
    var dataArray: List<CharArray> = data.map {
        it.toCharArray()
    }

    while (found != 0) {
        found = 0
        println("*".repeat(35))
        dataArray.forEachIndexed { lineNum, line ->
            // check the adjacent characters
            line.forEachIndexed { pos, char ->
                if (char == '@') {
                    var sum: Int
                    val aboveLine = dataArray.getOrNull(lineNum - 1)
                    val bottomLine = dataArray.getOrNull(lineNum + 1)

                    val topLeft = aboveLine?.getOrNull(pos - 1)
                    val topMid = aboveLine?.getOrNull(pos)
                    val topRight = aboveLine?.getOrNull(pos + 1)
                    val left = line.getOrNull(pos - 1)
                    val right = line.getOrNull(pos + 1)
                    val bottomLeft = bottomLine?.getOrNull(pos - 1)
                    val bottomMid = bottomLine?.getOrNull(pos)
                    val bottomRight = bottomLine?.getOrNull(pos + 1)

                    sum = listOf(
                        topLeft, topMid, topRight,
                        left, right,
                        bottomLeft, bottomMid, bottomRight
                    ).filter { it == '@' || it == 'x' }
                        .size

                    if (sum < 4) {
                        output++
                        found++;
                        line[pos] = 'x'
                    }
                }
            }
        }
        dataArray.forEach {
            println(it.contentToString())
        }
        dataArray = dataArray.map {
            it.map { char ->
                if (char == 'x') {
                    '.'
                } else {
                    char
                }
            }.toCharArray()
        }
    }

    println("part 2: $output")
}

fun main() {
    val data = File("data/day4.txt").readLines()

    part1(data)
    part2(data)
}