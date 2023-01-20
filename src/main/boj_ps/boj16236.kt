import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue

fun main() = with(System.`in`.bufferedReader()) {
	val N = readLine().toInt()
	var arr = Array(N) { IntArray(N) { 0 } }
	var visited = Array(N) { BooleanArray(N) { false } }
	var start = Pair(0, 0)

	for (i in 0 until N) {
		arr[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
		if (arr[i].contains(9)) {
			start = Pair(i, arr[i].indexOf(9))
		}
	}

	// 아래 오 왼 위 순서
	var dxdy = arrayOf(1 to 0, 0 to 1, 0 to -1, -1 to 0)
	fun findPrey(x: Int, y: Int, size: Int): Triple<Int, Int, Int> {
		var answer = Triple(-1, -1, 0)
		var visitedBFS = Array(N) { BooleanArray(N) { false } }

		var q =
			PriorityQueue(compareBy<Triple<Int, Int, Int>> { it.third }.thenBy { it.first }
				.thenBy { it.second })
		q.add(Triple(x, y, 0))
		visitedBFS[x][y] = true

		while (q.isNotEmpty()) {
			var front = q.poll()

			// front의 물고기를 먹을 수 있으면(아직 먹지 않았고, size가 작을 경우)
			if (!visited[front.first][front.second] && arr[front.first][front.second] < size && arr[front.first][front.second] != 0) {
				answer = front
				break
			}
			for (i in dxdy) {
				var dx = front.first + i.first
				var dy = front.second + i.second
				// OutofIndex, 방문체크, 지나갈 수 있는지 체크
				if (dx < 0 || dx >= N || dy < 0 || dy >= N) {
					continue
				}
				if(!visitedBFS[dx][dy] && arr[dx][dy] <= size){
					q.add(Triple(dx, dy, front.third + 1))
					visitedBFS[dx][dy] = true
				}

			}
		}
		return answer
	}


	var answer = 0
	fun dfs(x: Int, y: Int, size: Int, remain: Int, time: Int) {
		var nextPrey = findPrey(x, y, size)
//		println("${nextPrey.first} ${nextPrey.second}")

		// 더 먹을 수 있는 물고기가 없을 때
		if (nextPrey == Triple(-1, -1, 0)) {
			return
		}

		answer += nextPrey.third
		visited[x][y] = true
		var newRemain = if (arr[nextPrey.first][nextPrey.second] == size) remain - 1 else remain
		var newSize = size

		if(newRemain == 0){
			newSize = size + 1
			newRemain = newSize
		}

		dfs(nextPrey.first, nextPrey.second, newSize, newRemain, time + nextPrey.third)
		visited[nextPrey.first][nextPrey.second] = true
		println("${nextPrey.first}, ${nextPrey.second}, ${newSize}, $newRemain, $answer")
	}

	dfs(start.first, start.second, 2, 2, 0)
	print(answer)
}