package gold3

import java.lang.Integer.max
import java.util.*

/*
학생이 사는 마을 N
단방향 도로 M
i번째 길을 지나는 데 Ti의 시간 소요
X에 갔다가 다시 돌아온다
N명의 학생이 가장 짧은 시간에 다녀오는데, 이때 이 중 가장 많은 시간을 소요하는 학생은?
N 1000
M 10000
all to X, X to all O(N^3) -> N: 500

all to one(X), one(X) to all
all to one -> one to one * N O(V^2) -> 다익스트라 * N? -> 1억........시간초과 날듯
one to all -> 다익스트라 O(V^2)? -> 정방
일단 플로이드-워셜로,,,
비용의 최댓값? 1000 * 10000 -> 10000000
플로이드 시간초과 ㅠㅠ...
역방향 다익스트라? -> 따로 저장해야 함
단방향으로 one to all -> 다익스트라
그래프를 역방향 - 간선을 역방향으로 저장
one to all -> all to one
 */
fun main() = with(System.`in`.bufferedReader()) {
    val INF = 1000000000
    var (N, M, X) = readLine().trim().split(" ").map { it.toInt() }

    // 역방향으로 저장 all to one
    var graphToX = Array(N + 1) { mutableListOf<Pair<Int, Int>>() }

    // 정방향으로 저장 one to all
    var graphFromX = Array(N + 1) { mutableListOf<Pair<Int, Int>>() }

    var costToX = (0..N).map { INF }.toMutableList()
    var costFromX = (0..N).map { INF }.toMutableList()

    repeat(M) {
        var (start, end, c) = readLine().trim().split(" ").map { it.toInt() }
        graphFromX[start].add(Pair(end, c))
        graphToX[end].add(Pair(start, c))
    }

    fun searchToX() {
        val q = PriorityQueue<Pair<Int, Int>> { o1, o2 -> o1.second - o2.second }
        q.add(Pair(X, 0))
        costToX[X] = 0
        while (q.isNotEmpty()) {
            var (now, dis) = q.poll()
            if (costToX[now] < dis) continue
            for (i in graphToX[now]) {
                var cost = dis + i.second
                if (cost < costToX[i.first]) {
                    costToX[i.first] = cost
                    q.add(Pair(i.first, cost))
                }
            }
        }
    }

    fun searchFromX() {
        val q = PriorityQueue<Pair<Int, Int>> { o1, o2 -> o1.second - o2.second }
        q.add(Pair(X, 0))
        costFromX[X] = 0
        while (q.isNotEmpty()) {
            var (now, dis) = q.poll()
            if (costFromX[now] < dis) continue
            for (i in graphFromX[now]) {
                var cost = dis + i.second
                if (cost < costFromX[i.first]) {
                    costFromX[i.first] = cost
                    q.add(Pair(i.first, cost))
                }
            }
        }
    }

    searchToX()
    searchFromX()

    var answer = 0

    for (i in 1..N) {
        answer = max(answer, costToX[i] + costFromX[i])
    }
    print(answer)
}