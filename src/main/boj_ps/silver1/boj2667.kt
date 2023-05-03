package silver1

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()
    var answer = 0
    var count = 0

    var answerpq = mutableListOf<Int>()
    var map = Array(N) { IntArray(N) }
    var dxdy = arrayOf(0 to 1, 1 to 0, -1 to 0, 0 to -1)

    repeat(N) { i ->
        map[i] = readLine().toCharArray().map { it.code - '0'.code }.toIntArray()
    }

    var visited = Array(N) { BooleanArray(N) }

    fun dfs(now: Pair<Int, Int>) {
        visited[now.first][now.second] = true
        count++

        for (i in 0 until 4) {
            var dx = now.first + dxdy[i].first
            var dy = now.second + dxdy[i].second

            if (dx !in 0 until N || dy !in 0 until N || visited[dx][dy] || map[dx][dy] != 1) {
                continue
            }

            dfs(dx to dy)
//            visited[dx][dy] = false
        }
    }

    for (i in 0 until N) {
        for (j in 0 until N) {
            if (map[i][j] != 0 && !visited[i][j]) {
                answer++
                count = 0
                dfs(i to j)
                answerpq.add(count)
            } else {
                visited[i][j] = true
            }
        }
    }

    println(answer)

    answerpq.sorted().forEach {
        println(it)
    }
}