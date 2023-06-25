package silver4

/*
온세상이디피다
3 6 9 12 15
5 10 15
3+5 8
* */
fun main() = with(System.`in`.bufferedReader()) {
	var N = readLine().toInt()
	var dp = IntArray(5001) { -1 }

	for (i in 1..5000 / 3) {
		dp[i * 3] = i
	}
	for (i in 1..1000) {
		dp[i * 5] = i
	}

	for (i in 1..5000 / 8) {
		dp[i * 8] = i * 2
	}

	for (i in 1..5000) {
		if (dp[i] == -1) continue

		if (i + 3 <= 5000) {
			dp[i + 3] = if (dp[i + 3] == -1) dp[i] + 1 else Math.min(dp[i] + 1, dp[i + 3])
		}
		if (i + 5 <= 5000) {
			dp[i + 5] = if (dp[i + 5] == -1) dp[i] + 1 else Math.min(dp[i] + 1, dp[i + 5])
		}
		if (i + 8 <= 5000) {
			dp[i + 8] = if (dp[i + 8] == -1) dp[i] + 2 else Math.min(dp[i] + 2, dp[i + 8])
		}
	}

	println(dp[N])
}