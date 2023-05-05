package silver1

import java.util.*

// BFS가 정석 풀이, 그러나 DFS도 되는듯
// 연관문제 boj1260
fun main() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(" ").map { it.toInt() }

    var tree = ArrayList<MutableList<Int>>()
    repeat(N + 1) {
        tree.add(mutableListOf())
    }

    for (i in 0 until M) {
        var (X, Y) = readLine().split(" ").map { it.toInt() }
        tree[Y].add(X)
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
            for (node in tree[now]) {
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
    for (i in answerlist.indices) {
        if (answerlist[i] == maxValue) print("$i ")
    }
}