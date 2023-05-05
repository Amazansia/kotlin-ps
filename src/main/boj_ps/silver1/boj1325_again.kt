package silver1

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(" ").map { it.toInt() }

    var nodes = ArrayList<MutableList<Int>>()
    repeat(N + 1) {
        nodes.add(mutableListOf())
    }

    for (i in 0 until M) {
        var (X, Y) = readLine().split(" ").map { it.toInt() }
        nodes[Y].add(X)
    }

    // 정답 저장 리스트
    var answerlist = IntArray(N + 1) { 0 }

    // 가장 많은 컴퓨터 해킹: BFS - queue로 구현
    // prim 알고리즘과 비슷하게 동작
    for (i in 1..N) {
        var q: Queue<Int> = LinkedList()
        var visited = BooleanArray(N + 1)
        q.add(i)
        visited[i] = true

        while (q.isNotEmpty()) {
            var now = q.poll()
            for (node in nodes[now]) {
                if (node == 0) continue
                if (!visited[node]) {
                    answerlist[i]++
                    q.add(node)
                    visited[node] = true
                }
            }
        }
    }

    // 리스트에서 정답 찾기
    var maxValue = answerlist.maxOrNull()
    var bw = System.`out`.bufferedWriter()
    for (i in answerlist.indices) {
        if (answerlist[i] == maxValue) bw.append("$i ")
    }
    bw.flush()
    bw.close()
}