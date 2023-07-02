package silver3

import java.util.LinkedList
import java.util.Queue

fun main() = with(System.`in`.bufferedReader()) {
	var N = readLine().toInt()
	var K = readLine().toInt()
	var list = Array(N + 1) { mutableListOf<Int>() }
	for (i in 1..K) {
		var (s, e) = readLine().split(" ").map { it.toInt() }
		list[s].add(e)
		list[e].add(s)
	}

	var visited = BooleanArray(N + 1)
	visited[0] = true

	var answer = 0
	var q: Queue<Int> = LinkedList()

	q.add(1)

	while (q.isNotEmpty()) {
		var now = q.poll()
		visited[now] = true
		for (i in list[now]) {
			if (!visited[i]){
				q.add(i)
				visited[i] = true
				answer++
			}
		}
	}

	println(answer)
}