package silver1

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    var (N, M) = readLine().split(" ").map { it.toInt() }
    var maze = Array(N) { IntArray(M) }
    var count = Array(N) { IntArray(M) { 987654321 } }
    var visited = Array(N) { BooleanArray(M) }


    repeat(N) { i ->
        maze[i] = readLine().toCharArray().map { it.code - '0'.code }.toIntArray()
    }

    var answer = 0
    var now = 0 to 0
    var dxdy = arrayOf(1 to 0, 0 to 1, -1 to 0, 0 to -1)

    var pq = PriorityQueue(compareBy<Pair<Int, Int>> { count[it.first][it.second] })

    pq.add(now)
    visited[0][0] = true
    count[0][0] = 1

    while (pq.isNotEmpty()) {
        now = pq.poll()
        for (i in 0 until 4) {
            var dx = now.first + dxdy[i].first
            var dy = now.second + dxdy[i].second

            if (dx !in 0 until N || dy !in 0 until M || visited[dx][dy] || maze[dx][dy] == 0) {
                continue
            }
            count[dx][dy] = kotlin.math.min(count[now.first][now.second] + 1, count[dx][dy])
            visited[dx][dy] = true
            pq.add(dx to dy)
        }
    }

    println(count[N - 1][M - 1])
}