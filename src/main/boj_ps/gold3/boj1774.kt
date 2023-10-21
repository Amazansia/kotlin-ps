package gold3

import kotlin.math.abs
import kotlin.math.sqrt


/*
정신적인 통로의 길이들의 합...
이미 존재하는 통로는 비용이 0
크루스칼 알고리즘으로 간선선택
간선길이 계산하는 함수 만들어야 편할듯
연결그래프 하나에 다른 정점들을 연결하는 최소비용을 구하면...
N^2으로 찾아도 가능은 할듯
1000000000
N^3으로 찾아도 가능...?
1 000 000 000 -> 10억
안된다
N^2logN까지는 될듯
1000x1000으로 인접배열로 거리를 저장한다면...

함수
1. 간선길이 계산하는 함수
2. uf에 포함된 정점 중 주어진 정점에서 가장 가까운 거리를 가진 정점까지의 거리를 구하는 함수

MST를 구하는데 통로 몇개는 이미 정해져있는?
가중치 오름차순 정렬
0 -> 이미 연결된 우주신
n -> dist에 다 계산해서 넣기

Triple(start, end, distance)
distance로 정렬


거리 최댓값
2 000 000 000 000
2000억
Long으로 받아야함
* */
fun main() = with(System.`in`.bufferedReader()) {
    var (N, M) = readln().split(" ").map { it.toInt() }

    var location = Array(N) { IntArray(2) }
    var dist = Array(N) { DoubleArray(N) }

    for (i in 0 until N) {
        location[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    fun getDistance(a: IntArray, b: IntArray): Double {
        var x = abs(a[0] - b[0]).toLong()
        var y = abs(a[1] - b[1]).toLong()
        return sqrt((x * x + y * y).toDouble())
    }

    var parent = IntArray(N) { it }
    fun find(node: Int): Int {
        if (parent[node] == node) return node
        return find(parent[node])
    }

    fun union(a: Int, b: Int) {
        var rootA = find(a)
        var rootB = find(b)

        if (rootA < rootB) parent[rootB] = rootA
        else parent[rootA] = rootB
    }

    for (i in 0 until N) {
        for (j in 0 until N) {
            dist[i][j] = getDistance(location[i], location[j])
        }
    }
    // 이미 연결되어 있는 신들은 union으로 모두 연결시켜준다
    for (i in 0 until M) {
        var (s, e) = readLine().split(" ").map { it.toInt() - 1 }
        dist[s][e] = 0.0
        dist[e][s] = 0.0
        union(s, e)
    }

    var edgeList = mutableListOf<Triple<Int, Int, Double>>()

    for (i in 0 until N) {
        for (j in 0 until N) {
            edgeList.add(Triple(i, j, dist[i][j]))
        }
    }

    edgeList.sortBy { it.third }

    var answer: Double = 0.0
    var count = 1

    for (i in edgeList) {
        if (find(i.first) != find(i.second)) {
            union(i.first, i.second)
            count++
            answer += i.third
        }
//        if (count == N) break
    }

    println("%.2f".format(answer))
}