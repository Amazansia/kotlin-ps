package gold4

import java.util.*

/*
다익스트라?
플로이드워셜 800^3... 5억
N 500 -> 500^3... 1억
워셜 아닌듯
다익스트라로 1 -> N
1 -> one -> two -> N
1 -> two -> one -> N
둘중 최솟값 구하면 될듯
v1,v2가 시작, 끝점인 경우가 존재하네요..

* */
fun main() = with(System.`in`.bufferedReader()) {
    var (N, E) = readLine().split(" ").map { it.toInt() }
    var edge = Array(N + 1) { mutableListOf<Pair<Int, Int>>() }

    repeat(E) {
        var (s, e, c) = readLine().split(" ").map { it.toInt() }
        edge[s].add(e to c)
        edge[e].add(s to c)
    }

    var (one, two) = readLine().split(" ").map { it.toInt() }

    fun dijkstra(start: Int, end: Int): Int {
        if (start == end) return 0
        var distance = (0..N).map { Int.MAX_VALUE }.toIntArray()
        var pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        pq.add(start to 0)
        distance[start] = 0

        while (pq.isNotEmpty()) {
            var (now, dis) = pq.poll()
            if (distance[now] < dis) continue
            for (i in edge[now]) {
                var cost = dis + i.second
                if (cost < distance[i.first]) {
                    distance[i.first] = cost
                    pq.add(i.first to cost)
                }
            }
        }
        return distance[end]
    }

    var Itoone = dijkstra(1, one)
    var onetotwo = dijkstra(one, two)
    var twotoN = dijkstra(two, N)
    var onetwo: Long = Itoone + onetotwo + twotoN.toLong()
    if (Itoone == Int.MAX_VALUE || onetotwo == Int.MAX_VALUE || twotoN == Int.MAX_VALUE) {
        onetwo = -1
    }

    var Itotwo = dijkstra(1, two)
    var twotoone = dijkstra(two, one)
    var onetoN = dijkstra(one, N)
    var twoone: Long = Itotwo + twotoone + onetoN.toLong()
    if (Itotwo == Int.MAX_VALUE || twotoone == Int.MAX_VALUE || onetoN == Int.MAX_VALUE) {
        twoone = -1
    }

    var answer = kotlin.math.min(onetwo, twoone)
    println(if (answer >= Int.MAX_VALUE) -1 else answer)
}
