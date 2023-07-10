package gold3/*
상하좌우로 이동가능
이동가능한 경로의 수를 출력한다.
제일 왼쪽위칸에서 제일 오른쪽아래칸으로 이동하는 경로의 수
항상 높이가 더 낮은 지점으로만 이동하려고 한다
정답은 10억 이하의 정수로 나온다...
BFS?일듯
아님말고
탐색방향이 글러먹음
i j 순차탐색했더니 뒤로 빠꾸할경우를 못잡는다
dxdy기준으로 dp저장해도 두번 연속으로 하->상 올라올 경로를 못찾을듯

dfs+메모이제이션?
dp[x][y]: x,y를 방문할 수 있는 경로의 수
생각해보면 최대 경로의 수가 10억이므로 무조건 dp써야함...그러넹 ㅎ
초기조건
dp[0][0] = 1
0 0 true
0 1 주변에 50true, 50false, 37false. 내리막길이면서 true면 dp[0][0]더해주면 되고, 37은 내리막길이면서 false이므로 dfs[0][2]돌려준다
문제의 20...
dp[1][3]. 주변에 32true, 40false, 25false, 17false
32는 dp에서 데려오면 되고 40은 0
* */

fun main() = with(System.`in`.bufferedReader()) {
	var (N, M) = readLine().split(" ").map { it.toInt() }

	val dxdy = arrayOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)

	var map = Array(N) { IntArray(M) }
	for (i in 0 until N) {
		map[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
	}

	var dp = Array(N) { IntArray(M) }
	var visited = Array(N) { BooleanArray(M) }


	//	칸에서 경로를 끌어올 때 -> 자기보다 크면서&true인 칸은 dp배열에서 값을 가져와서 더해준다
//	자기보다 크면서&false인 칸은 dfs로 값을 가져와준다
//			자기보다 작으면서 false인 칸으로 진행한다
//	자기보다 작으면서 true인 칸은 진행하지 않는다
	fun dfs(x: Int, y: Int): Int {

		if (visited[x][y]) return dp[x][y]

		// 방문하지 않았다면 값을 가져와야 함
		visited[x][y] = true

		for (dd in dxdy) {
			var dx = x + dd.first
			var dy = y + dd.second

			if (dx in 0 until N && dy in 0 until M) {
				if (map[x][y] < map[dx][dy]) dp[x][y] += dfs(dx, dy)
			}
		}

		return dp[x][y]
	}

	dp[0][0] = 1
	visited[0][0] = true

	for (i in 0 until N) {
		for (j in 0 until M) {
			dp[i][j] = dfs(i, j)
		}
	}

	println(dp[N - 1][M - 1])
}