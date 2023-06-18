package gold2

import java.util.*

/*
1->3 :2
3->1 :2
V 100000, D 10000
1 000 000 000 -> 10억, int 이내
트리의 정의: 무방향 그래프이면서 사이클이 없는
한 노드에서 BFS를 통해 가장 먼 노드를 찾는다.
찾아낸 "가장 먼 노드"에서 한번 더 BFS를 통해 가장 먼 노드를 찾는다.
거리 합의 최댓값이 트리의 지름: 두 점 사이의 거리 중 가장 긴 것이 된다.
*
* */

fun main() = with(System.`in`.bufferedReader()) {
    var V = readLine().toInt()
    var tree = Array(V + 1) { LinkedList<Pair<Int, Int>>() }

    repeat(V) {
        var rline = readLine().trim().split(" ").map { it.toInt() }.dropLast(1)
        var len = rline.size / 2
        for (i in 1..len) {
            tree[rline[0]].add(Pair(rline[i * 2 - 1], rline[i * 2]))
        }
    }

    var edge = 0 to 0

    fun bfs(start: Int) {
        var visited = BooleanArray(V + 1)

        // 내림차순 정렬
        var pq = PriorityQueue<Pair<Int, Int>> { o1, o2 -> o1.second - o2.second }
        pq.add(start to 0)
        visited[start] = true
        var now = 0 to 0

        while (pq.isNotEmpty()) {
            now = pq.poll()
            for (i in tree[now.first]) {
                if (!visited[i.first]) {
                    pq.add(i.first to now.second + i.second)
                    visited[i.first] = true
                }
            }
        }
        edge = now
    }

    bfs(1)
    bfs(edge.first)
    println(edge.second)
}