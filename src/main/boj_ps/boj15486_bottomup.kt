import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
	var N = readLine().toInt()
	var arr = Array(N + 1) { IntArray(2) { 0 } }
	for (i in 0 until N) {
		arr[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
	}

	var dp = IntArray(N + 1)
	var ans = 0
	var temp = 0

	// 앞에서부터 채워 나가는 방식, 상향식
	for (i in 0..N) {
		var endDay = i + arr[i][0]
		temp = max(temp, dp[i])

		if (endDay > N) {
			continue
		}
		dp[endDay] = max(dp[endDay], temp + arr[i][1])
		ans = max(ans, dp[endDay])
	}

	print(ans)
}