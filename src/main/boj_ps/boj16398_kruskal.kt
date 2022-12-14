import java.util.*

// kruskal algorithm(union-find)
// E log E
// 이 문제에서 E = N(N - 1)/2 이므로, prim보다 빠를 수 없다...고 예상됨

lateinit var parent: IntArray

private fun union(_x: Int, _y: Int) {
	var x = find(_x)
	var y = find(_y)

	if (x < y) parent[y] = x
	else parent[x] = y
}

private fun find(x: Int): Int {
	if (parent[x] == x) return x
	// 이거 재귀할때 dp로 저장 안해주면 시간초과남
	parent[x] = find(parent[x])
	return parent[x]
}

fun main() = with(System.`in`.bufferedReader()) {

	val N = readLine().toInt()

	var graph = ArrayList<IntArray>()

	for (i in 0 until N) {
		var arr = readLine().split(" ").map { it.toInt() }
		for (j in arr.indices) {
			if (i == j) continue
			graph.add(intArrayOf(i, j, arr[j]))
		}
	}

	// cost 기준으로 오름차순 정렬
	graph.sortBy { it[2] }

	parent = IntArray(N) { it }
	var sum: Long = 0
	for (i in graph.indices) {
		if (find(graph[i][0]) != find(graph[i][1])) {
			sum += graph[i][2]
			union(graph[i][0], graph[i][1])
		}
	}
	println(sum)
}