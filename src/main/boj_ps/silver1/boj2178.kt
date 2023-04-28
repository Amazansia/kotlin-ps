package silver1

fun main() = with(System.`in`.bufferedReader()) {
    var (N, M) = readLine().split(" ").map { it.toInt() }
    var maze = Array(N) { IntArray(M) }
    var visited = Array(N) { BooleanArray(M) }

    repeat(N) { i ->
        maze[i] = readLine().toCharArray().map { it.code - '0'.code }.toIntArray()
    }

    var answer = Int.MAX_VALUE
    var dxdy = arrayOf(1 to 0, 0 to 1, -1 to 0, 0 to -1)

    fun dfs(count: Int, now: Pair<Int, Int>) {
        visited[now.first][now.second] = true
        if (now.first == N - 1 && now.second == M - 1) {
            answer = kotlin.math.min(answer, count)
            return
        }

        for (i in 0 until 4) {
            var dx = now.first + dxdy[i].first
            var dy = now.second + dxdy[i].second

            if (dx !in 0 until N || dy !in 0 until M || visited[dx][dy]) {
                continue
            }

            if (maze[dx][dy] == 0)
                continue

            dfs(count + 1, dx to dy)
            visited[dx][dy] = false
        }
    }

    dfs(1, 0 to 0)
    println(answer)
}