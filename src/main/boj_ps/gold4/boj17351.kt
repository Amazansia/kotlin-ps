package gold4

import kotlin.math.*

/*
250000

M에서 출발, A에서 끝
M에서 출발해서 A로 끝나는 좌표 start, end쌍을 List에 저장
조건을 만족하는 M to A를 찾아가면서 최대의 answer 출력
갈 수 있는 경로의 수는 2^500????????????

dp?
dp[3][0] =
dp[1][2] = 1
12341
23002
04124
00034
1234
갈 수 있는 경로의 길이는 한번에 최대 500

3차원 dp
dp[i][j][1~4]


* */

fun main() = with(System.`in`.bufferedReader()) {
	var N = readLine().toInt()

	// 숫자순서로 변경
	var arr = Array(N) { IntArray(N) }
	for (i in 0 until N) {
		arr[i] = readLine().toCharArray().map {
			when (it) {
				'M' -> 1
				'O' -> 2
				'L' -> 3
				'A' -> 4
				else -> 0
			}
		}.toIntArray()
	}

	var dp = Array(N) { Array(N) { IntArray(5) { 0 } } }

	for (i in 0 until N) {
		for (j in 0 until N) {
			if (arr[i][j] != 0) dp[i][j][arr[i][j]] = 1
		}
	}

	/*
	dp[i][j][k] = max(dp[i-1][j][k-1], dp[i][j-1][k-1]) + 1
	0,0,1 = 1
	0,1,2 = 1
	dp[i][j][k]: arr[i][j]에 k라는 값이 저장되어 있다 t:1/f:0
	MOLA를 완성하기 위해서는
	연속적으로
	arr[i][j] = 1
	arr[i+1][j] = 2
	arr[i+2][j] = 3
	arr[i+3][j] = 4
	이런식으로 저장되어 있어야 함

	예제입력 2의 1,1에는
	dp[0][0][1] = 1
	dp[0][1][2] == 1 -> 계속 진행, dp[]
	dp[1][0][2] == 1 -> 계속 진행

	dp[0][1][2] = 1
	dp[1][1][3] = 1
	dp[2][1][4] = 1
	...
	1234 연속적으로 1인 경우가 존재한다면 dp[i][j][0]++

	0,0에서
	dp[0][0][1] = 1

	dp[0][0][1] = 1
	dp[0][1][2] = 2여야 함

	dp[0][1][2] -> 1: dp[0][1][2] = max(dp[0][1][2] + 1, dp[0][1][2])
	dp[1][0][2] -> 0: continue(연결불가)
	... 이렇게 K로? 돌려서
	dp[i][j][4] == 4라면
	dp[i][j][0] = max(dp[i][j][0] + 1,dp[i-1][j][0], dp[i][j-1][0])

	dp[i][j][0]: i,j가 가질 수 있는 가장 많은 MOLA의 개수

	k가 1234 연속적으로 1인 경우가 존재해야 함
	이럴 경우 dp[2][1][0] = 1로 업데이트
	최종적으로 dp[4][3][0] = 2
	dp[3][4][0] = 2
	dp[i][j][0] = max(dp[i-1][j][0], dp[i][j-1][0])
	4,1에서 dp[i][j]가 갱신되면 안됨. 즉 이미 1234까지의 완성이 진행되고 있는데 두번 세면 안됨
	그럼 갱신할 수 있는 경우는
	k==1일 때의 dp[i][j][0]에서
	* */
	var answer = 0

	for (i in 0 until N) {
		for (j in 0 until N) {
			for (k in 2 until 5) {
				if (dp[i][j][k] == 0) continue
				if (i - 1 in 0 until N && dp[i - 1][j][k - 1] == k - 1 || j - 1 in 0 until N && dp[i][j - 1][k - 1] == k - 1) {
					dp[i][j][k] = k
				}
			}
			// 문자열이 완성될 경우
			if (dp[i][j][4] == 4) {
//				if (i == N - 1 || j == N - 1) {
//					answer = Math.max(answer, dp[i][j][0] + 1)
//				}
				answer = Math.max(answer, dp[i][j][0])
				if (i + 1 in 0 until N) {
					dp[i + 1][j][0] = max(dp[i][j][0] + 1, dp[i + 1][j][0])
				}
				if (j + 1 in 0 until N) {
					dp[i][j + 1][0] = max(dp[i][j][0] + 1, dp[i][j + 1][0])
				}
			}
			// 완성되지 않을 경우
			else {
				if (i + 1 in 0 until N) {
					dp[i + 1][j][0] = max(dp[i][j][0], dp[i + 1][j][0])
				}
				if (j + 1 in 0 until N) {
					dp[i][j + 1][0] = max(dp[i][j][0], dp[i][j + 1][0])
				}
			}
		}
	}

	dp

	println(dp[N-1][N-1][0])
}