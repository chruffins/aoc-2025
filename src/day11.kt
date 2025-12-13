import java.io.File


private fun part1(data: List<String>) {
    var output: Long = 0

    val reachableFrom: MutableMap<String, Set<String>> = mutableMapOf()
    val vertices: MutableList<String> = mutableListOf()

    data.forEach { line ->
        val (src, right) = line.split(":")
        val dests = right.trim().split(" ").filter { it.isNotEmpty() }

        reachableFrom[src] = dests.toSet()
        vertices.add(src)
        vertices.addAll(dests)
    }

    // put sinks in the map
    for (v in vertices) {
        reachableFrom.putIfAbsent(v, emptySet())
    }

    val indegree = mutableMapOf<String, Int>().withDefault { 0 }

    for ((_, vs) in reachableFrom) {
        for (v in vs) {
            indegree[v] = indegree.getValue(v) + 1
        }
    }

    val queue = ArrayDeque<String>()
    for (v in vertices) {
        if (indegree.getValue(v) == 0) {
            queue.add(v)
        }
    }

    val topo = mutableListOf<String>()
    while (queue.isNotEmpty()) {
        val u = queue.removeFirst()
        topo.add(u)

        for (v in reachableFrom[u]!!) {
            indegree[v] = indegree.getValue(v) - 1
            if (indegree.getValue(v) == 0) {
                queue.add(v)
            }
        }
    }

    val start = "you"
    val end = "out"

    val dp = mutableMapOf<String, Long>().withDefault { 0L }
    dp[start] = 1L

    for (u in topo) {
        for (v in reachableFrom[u]!!) {
            dp[v] = dp.getValue(v) + dp.getValue(u)
        }
    }

    output = dp.getValue(end)

    println("part 1: $output")
}

private fun part2(data: List<String>) {
    var output: Long = 0

    val reachableFrom: MutableMap<String, Set<String>> = mutableMapOf()
    val vertices: MutableList<String> = mutableListOf()

    data.forEach { line ->
        val (src, right) = line.split(":")
        val dests = right.trim().split(" ").filter { it.isNotEmpty() }

        reachableFrom[src] = dests.toSet()
        vertices.add(src)
        vertices.addAll(dests)
    }

    // put sinks in the map
    for (v in vertices) {
        reachableFrom.putIfAbsent(v, emptySet())
    }

    val indegree = mutableMapOf<String, Int>().withDefault { 0 }

    for ((_, vs) in reachableFrom) {
        for (v in vs) {
            indegree[v] = indegree.getValue(v) + 1
        }
    }

    val queue = ArrayDeque<String>()
    for (v in vertices) {
        if (indegree.getValue(v) == 0) {
            queue.add(v)
        }
    }

    val topo = mutableListOf<String>()
    while (queue.isNotEmpty()) {
        val u = queue.removeFirst()
        topo.add(u)

        for (v in reachableFrom[u]!!) {
            indegree[v] = indegree.getValue(v) - 1
            if (indegree.getValue(v) == 0) {
                queue.add(v)
            }
        }
    }

    val start = "svr"
    val end = "out"
    val n1 = "dac"
    val n2 = "fft"

    val dp = mutableMapOf<String, Long>().withDefault { 0L }
    dp[start] = 1L

    for (u in topo) {
        for (v in reachableFrom[u]!!) {
            dp[v] = dp.getValue(v) + dp.getValue(u)
        }
    }

    val dpFromStart = dp

    val dpFromN1 = mutableMapOf<String, Long>().withDefault { 0L }
    dpFromN1[n1] = 1L
    for (u in topo) {
        for (v in reachableFrom[u]!!) {
            dpFromN1[v] = dpFromN1.getValue(v) + dpFromN1.getValue(u)
        }
    }

    val dpFromN2 = mutableMapOf<String, Long>().withDefault { 0L }
    dpFromN2[n2] = 1L
    for (u in topo) {
        for (v in reachableFrom[u]!!) {
            dpFromN2[v] = dpFromN2.getValue(v) + dpFromN2.getValue(u)
        }
    }

    val pathsViaN1ThenN2 =
        dpFromStart.getValue(n1) *
                dpFromN1.getValue(n2) *
                dpFromN2.getValue(end)

    val pathsViaN2ThenN1 =
        dpFromStart.getValue(n2) *
                dpFromN2.getValue(n1) *
                dpFromN1.getValue(end)

    output = pathsViaN1ThenN2 + pathsViaN2ThenN1

    println("part 2: $output")
}

fun main() {
    val data = File("data/day11.txt").readLines()

    part1(data)
    part2(data)
}