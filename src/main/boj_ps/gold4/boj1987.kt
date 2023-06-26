package gold4

/*
같은 알파벳이 적힌 칸을 두번 지날 수 없다
알파벳 방문체크 arr
board 방문위치 체크 arr
DFS로 풀수있을까?
R/C 최대 20
최대 depth 400인줄 알앗으나 26
알파벳개수까지밖에 못가니까 가능할듯?
* */
fun main() = with(System.`in`.bufferedReader()) {
	var (R, C) = readLine().split(" ").map { it.toInt() }
	var board = Array(R) { CharArray(C) }

	for (i in 0 until R) {
		board[i] = readLine().toCharArray()
	}

	// 알파벳 방문체크 arr
	var alphabet = BooleanArray(26)
	// board 방문위치 체크 arr
	var visited = Array(R) { BooleanArray(C) }


	// 이동배열
	var dxdy = arrayOf(1 to 0, 0 to 1, -1 to 0, 0 to -1)
	var answer = 0

	fun dfs(cnt: Int, now: Pair<Int, Int>, alphabet: BooleanArray, visited: Array<BooleanArray>) {

		answer = Math.max(answer, cnt + 1)
		visited[now.first][now.second] = true
		alphabet[board[now.first][now.second] - 'A'] = true

		for (i in dxdy) {
			var dx = now.first + i.first
			var dy = now.second + i.second
			// board[dx][dy]가 범위를 벗어났거나 방문했거나 이미 지난 알파벳이라면 continue
			if (dx !in 0 until R || dy !in 0 until C || alphabet[board[dx][dy] - 'A'] || visited[dx][dy]) continue
			alphabet[board[dx][dy] - 'A'] = true
			visited[dx][dy] = true

			dfs(
				cnt + 1, dx to dy, alphabet, visited
			)

			alphabet[board[dx][dy] - 'A'] = false
			visited[dx][dy] = false
		}
	}

	dfs(0, 0 to 0, alphabet, visited)
	println(answer)

}