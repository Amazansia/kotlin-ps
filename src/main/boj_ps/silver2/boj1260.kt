package silver2

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (N, M, V) = readLine().split(" ").map { it.toInt() }
    var nodes = Array(N + 1) { ArrayList<Int>() }

    // ArrayList 초기화
    repeat(N + 1) {
        nodes[it] = ArrayList()
    }

    // 간선 정보 저장
    repeat(M) {
        var (start, end) = readLine().split(" ").map { it.toInt() }
        nodes[start].add(end)
        nodes[end].add(start)
    }

    for (i in 1..N) {
        nodes[i].sort()
    }

    var visited = BooleanArray(N + 1) { false }

    fun dfs(start: Int) {
        visited[start] = true
        print("$start ")
        for (i in nodes[start]) {
            if (!visited[i]) {
                dfs(i)
                visited[i] = true
            }
        }
    }

    fun bfs(start: Int) {
        visited = BooleanArray(N + 1) { false }
        var q: Queue<Int> = LinkedList()
        visited[start] = true
        q.add(start)

        while (q.isNotEmpty()) {
            var front = q.poll()
            print("$front ")

            for (i in nodes[front]) {
                if (!visited[i]) {
                    q.add(i)
                    visited[i] = true
                }
            }
        }

    }

    dfs(V)
    println()
    bfs(V)
}