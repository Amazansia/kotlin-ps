package gold4

import java.lang.Integer.max

/*
dp[i][j][k] = i,j번째에서 k번째 MOLA가 완성되었다 -> 안됨
시작위치를 기억하고 있어야 함...
역으로해야될듯
갈 수 있는 위치는 i,j에서 i-1,j / i, j-1밖에 없음
dp[i][j][k]
M이 나올 때 갈 수 있는 모든 위치를 고려해서
시작점의 위치를 가지고 있든가 아니면 갱신할 때 알 수 있든가...
둘다 힘들듯?
역으로 돌아간다면
안될듯
시작위치를 무조건 기억해야함
dfs는 안되나?
x,y에서 n-1,n-1까지 간다고 하면...
 */

fun main() = with(System.`in`.bufferedReader()) {
	var N = readLine().toInt()
	var arr = Array(N) { CharArray(N) }
	for (i in 0 until N) {
		arr[i] = readLine().toCharArray()
	}

	var dxdy = arrayOf(0 to 1, 1 to 0)

	var dp = Array(N) { Array(N) { IntArray(4) { -1 } } }

	fun dfs(x: Int, y: Int, str: Int): Int {

//		println("${arr[x][y]} $x $y ${dp[x][y][str]}")

		if (x == N - 1 && y == N - 1) {
//			if (arr[x][y] == 'A' && str == 3) return 1
			return 0
		}


		if (dp[x][y][str] != -1) {
			return dp[x][y][str]
		}

		dp[x][y][str] = 0

		for (i in dxdy) {
			var dx = x + i.first
			var dy = y + i.second

			if (dx !in 0 until N || dy !in 0 until N) {
				continue
			}

			if (arr[dx][dy] == 'M')
				dp[x][y][str] = max(dp[x][y][str], dfs(dx, dy, 1))
			else if (arr[dx][dy] == 'O' && str == 1) {
				dp[x][y][str] = max(dp[x][y][str], dfs(dx, dy, 2))
			} else if (arr[dx][dy] == 'L' && str == 2) {
				dp[x][y][str] = max(dp[x][y][str], dfs(dx, dy, 3))
			} else if (arr[dx][dy] == 'A' && str == 3) {
				dp[x][y][str] = max(dp[x][y][str], dfs(dx, dy, 0) + 1)
			} else {
				dp[x][y][str] = max(dp[x][y][str], dfs(dx, dy, 0))
			}
		}
		return dp[x][y][str]
	}

	println(if (arr[0][0] == 'M') dfs(0, 0, 1) else dfs(0, 0, 0))

	//println(dp.toString())
}