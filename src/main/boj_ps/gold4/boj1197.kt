package gold4

import java.util.*

/*
C는...음수일수도 있따...?
프림으로
* */

fun main() = with(System.`in`.bufferedReader()) {
    var (V, E) = readLine().split(" ").map { it.toInt() }

    var arr = Array(V + 1) { mutableListOf<Pair<Int, Int>>() }
    for (i in 0 until E) {
        var (a, b, c) = readLine().split(" ").map { it.toInt() }
        arr[a].add(b to c)
        arr[b].add(a to c)
    }

    var pq = PriorityQueue(compareBy<Pair<Int, Int>> { it.second })

    pq.add(1 to 0)
    var visited = BooleanArray(V + 1)
    visited[0] = true
    var sum = 0

    while (pq.isNotEmpty()) {
        var now = pq.poll()
        if (visited[now.first]) continue
        visited[now.first] = true
        sum += now.second
//        if (!visited.contains(false)) break
        for (i in arr[now.first]) {
            if (!visited[i.first]) {
                pq.add(i)
            }
        }
    }

    println(sum)
}