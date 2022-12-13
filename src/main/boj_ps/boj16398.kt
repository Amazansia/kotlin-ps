// prim algorithm
// 배열 구현, N^2
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

// kruskal algorithm(union-find)
// E log E
// 이 문제에서 E = N(N - 1)/2 이므로, prim보다 빠를 수 없다...고 예상됨