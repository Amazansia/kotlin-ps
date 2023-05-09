package gold4

import java.util.*

// prim algorithm
// 배열 구현, N^2
/*
fun main() = with(System.`in`.bufferedReader()) {
	val N = readLine().toInt()

	// 2차원 배열 초기화
	var arr = Array(N) { IntArray(N) { 0 } }
	// visited 배열 초기화
	var visited = BooleanArray(N) { false }
	// tree에 연결된 정점에서 자신으로의 최소 간선 비용 저장
	var minEdge = IntArray(N) { 0 }
	// 가중치 합계 - int 범위를 벗어날 수 있음에 주의
	var sum: Long = 0

	// 배열 안에 정보 저장
	for (i in 0 until N) {
		arr[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
	}

	minEdge.fill(987654321)
	minEdge[0] = 0

	for (i in 0 until N) {
		var minval = 987654321
		var minNode = 0

		for (j in 0 until N) {
			if (!visited[j] && minval > minEdge[j]) {
				minval = minEdge[j]
				minNode = j
			}
		}

		sum += minval
		visited[minNode] = true

		for (j in 0 until N) {
			if (!visited[j] && arr[minNode][j] != 0 && minEdge[j] > arr[minNode][j]) {
				minEdge[j] = arr[minNode][j]
			}
		}
	}
	println(sum)
}
*/

// prim algorithm
// 우선순위 큐(최소힙) 구현, E log N
class Node : Comparable<Node> {
    var w: Int = 0
    var cost: Int = 0

    constructor(w: Int, cost: Int) {
        this.w = w
        this.cost = cost
    }

    override fun compareTo(other: Node): Int {
        return this.cost - other.cost
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    // visited 배열 초기화
    var visited = BooleanArray(N) { false }
    // tree에 연결된 정점에서 자신으로의 최소 간선 비용 저장
    var minEdge = IntArray(N) { 0 }
    // 가중치 합계 - int 범위를 벗어날 수 있음에 주의
    var sum: Long = 0

    // Array<ArrayList>
    var graph = Array(N) { ArrayList<Node>(N) }

    // 배열 안에 정보 저장
    for (i in 0 until N) {
        var intarr = readLine().split(" ").map { it.toInt() }.toIntArray()
        for (j in intarr.indices) {
            graph[i].add(Node(j, intarr[j]))
            graph[i].add(Node(i, intarr[j]))
        }
    }

    var pq = PriorityQueue<Node>()

    pq.offer(Node(0, 0))

    while (pq.isNotEmpty()) {
        var node = pq.poll()

        var v = node.w
        var c = node.cost

        if (visited[v]) continue

        visited[v] = true
        sum += c

        for (n in graph[v]) {
            if (!visited[n.w]) {
                pq.add(n)
            }
        }
    }

    println(sum)
}