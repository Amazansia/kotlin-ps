fun main() = with(System.`in`.bufferedReader()) {
	val (N, K) = readLine().split(" ").map { it.toInt() }
	// 이항계수: nCk = N! / (N-K)!K! -> 이걸 10007로 나눈 나머지를 구하라...
	// 이항계수 공식: nCk = n-1Ck + n-1Ck-1 -> dp!
	var R = kotlin.math.min(N - K, K)

	var dp = Array(N + 1) { IntArray(N + 1) { 1 } }

	for (i in 2..N) {
		for (j in 1 until i) {
			dp[i][j] = ((dp[i - 1][j] % 10007) + (dp[i - 1][j - 1] % 10007)) % 10007
		}
	}

	if (N > 1 && K >= 1) {
		println(dp[N][R] % 10007)
	} else {
		print(1)
	}

}