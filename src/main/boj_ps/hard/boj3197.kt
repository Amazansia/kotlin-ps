package hard

import java.awt.Point
import java.util.LinkedList
import java.util.Queue

fun main() = with(System.`in`.bufferedReader()) {

	var dr = arrayOf(-1, 1, 0, 0)
	var dc = arrayOf(0, 0, -1, 1)

	var rline = readLine().split(" ")
	val row = rline[0].toInt()
	val col = rline[1].toInt()

	// 물과 인접한 얼음이 녹는다 -> 인접한 곳과의 상태변화가 있다면 BFS
	// 비슷한 유형의 14868. 문명을 풀어보자
	var lake = Array(row) { CharArray(col) { '0' } }
	var visited = Array(row) { BooleanArray(col) }

	var waterQ: Queue<Point> = LinkedList()
	var swanQ: Queue<Point> = LinkedList()

	var swan = Array(2) { Point() }
	// var swan = arrayOf(Point(), Point()) 와 동일하다

	var idx = 0
	for (i in 0 until row) {
		var line = readLine()
		for (j in 0 until col) {
			lake[i][j] = line[j]
			if (lake[i][j] == 'L') {
				lake[i][j] = '.'
				swan[idx++] = Point(i, j)
			}
			if (lake[i][j] == '.') {
				waterQ.add(Point(i, j))
			}
		}
	}

	swanQ.add(swan[0])
	visited[swan[0].x][swan[0].y] = true

	fun isRange(nr: Int, nc: Int): Boolean {
		if (nr in 0 until row && nc in 0 until col) return true
		return false
	}

	fun melt() {
		var size = waterQ.size
		for (i in 0 until size) {
			var now = waterQ.poll()

			for (k in 0 until 4) {
				var nr = now.x + dr[k]
				var nc = now.y + dc[k]

				if (isRange(nr, nc) && lake[nr][nc] == 'X') {
					lake[nr][nc] = '.'
					waterQ.add(Point(nr, nc))
				}
			}
		}
	}

	fun move_swan(): Boolean {
		var nextQ: Queue<Point> = LinkedList()
		while (!swanQ.isEmpty()) {
			var now = swanQ.poll()
			if (now.x == swan[1].x && now.y == swan[1].y) {
				return true
			}

			for (k in 0 until 4) {
				var nr = now.x + dr[k]
				var nc = now.y + dc[k]

				if (!isRange(nr, nc) || visited[nr][nc])
					continue
				visited[nr][nc] = true

				if (lake[nr][nc] == '.') {
					swanQ.add(Point(nr, nc))
				} else if (lake[nr][nc] == 'X') {
					nextQ.add(Point(nr, nc))
				}
			}
		}
		swanQ = nextQ
		return false
	}

	var day = 0
	while (true) {
		if (move_swan())
			break
		melt()
		day++
	}

	print(day)
}



