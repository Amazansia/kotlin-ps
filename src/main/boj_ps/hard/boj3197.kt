fun clone(origin: Array<CharArray>, row: Int, col: Int): Array<CharArray> {
	val clone = Array(row) { CharArray(col) }
	for (i in origin.indices) {
		clone[i] = origin[i].copyOf()
	}
	return clone
}

private var flag = false

fun main() = with(System.`in`.bufferedReader()) {
	// 구현과 dfs?
	// 날짜가 지남에 따라 변하는 호수의 상태를 구현하고 그 내부 for문에서
	// dfs로 길이 존재하는지 확인하면 될 듯
	// 구현, dfs 둘다 신경써야 하는 문제 같음
//	val move = arrayOf(arrayOf(-1, 1), arrayOf(-1, -1), arrayOf(1, 1), arrayOf(1, -1))

	var temp = readLine().split(" ")
	val row = temp[0].toInt()
	val col = temp[1].toInt()

	var lake = Array(row) { CharArray(col) { '0' } }

	var point = mutableListOf<Pair<Int, Int>>()

	for (i in 0 until row) {
		lake[i] = readLine().toCharArray()
		if (lake[i].contains('L')) {
			point.add(Pair(i, lake[i].indexOf('L')))
			if (lake[i].indexOf('L') != lake[i].lastIndexOf('L')) {
				point.add(Pair(i, lake[i].lastIndexOf('L')))
			}
		}
	}

	val start = point[0]
//	print(start)
	val end = point[1]
//	print(end)
	var i = 0

	while (true) {

		// dfs로 길이 존재하는지 확인
		// 길이 존재하면 답을 출력하고 종료
		var visited = Array(row) { BooleanArray(col) { false } }
		dfs(lake, start, end, visited, row, col, 0)
		if (flag) {
			print(i)
			return
		}

		i++
		// 날짜가 지남에 따라 호수의 상태 변화
		var templist = clone(lake, row, col)

		for (r in 0 until row) {
			for (c in 0 until col) {
				if (lake[r][c] == 'X') {
					if (r - 1 > 0 && c - 1 > 0 && r + 1 < row && c + 1 < col) {
						if (lake[r - 1][c] == '.' || lake[r + 1][c] == '.' || lake[r][c - 1] == '.' || lake[r][c + 1] == '.') {
							templist[r][c] = '.'
						}
					}
				}
			}
		}

		lake = templist
		println(i)
	}

}

fun dfs(
	lake: Array<CharArray>,
	start: Pair<Int, Int>,
	end: Pair<Int, Int>,
	visited: Array<BooleanArray>,
	row: Int,
	col: Int,
	depth: Int,
) {

	// 방문체크
	visited[start.first][start.second] = true

	// 길이 있다면 종료
	if (start.first == end.first && start.second == end.second) {
		flag = true
//		println()
//		print(start)
//		print(end)
		return
	}

	if (depth > row + col) {
		return
	}

	if (start.first + 1 < row && !visited[start.first + 1][start.second] && lake[start.first + 1][start.second] == '.') dfs(
		lake, Pair(start.first + 1, start.second), end, visited, row, col, depth + 1
	)
	if (start.first - 1 > 0 && !visited[start.first - 1][start.second] && lake[start.first - 1][start.second] == '.') dfs(
		lake, Pair(start.first - 1, start.second), end, visited, row, col, depth + 1
	)
	if (start.second + 1 < col && !visited[start.first][start.second + 1] && lake[start.first][start.second + 1] == '.') dfs(
		lake, Pair(start.first, start.second + 1), end, visited, row, col, depth + 1
	)
	if (start.second - 1 > 0 && !visited[start.first][start.second - 1] && lake[start.first][start.second - 1] == '.') dfs(
		lake, Pair(start.first, start.second - 1), end, visited, row, col, depth + 1
	)
}
