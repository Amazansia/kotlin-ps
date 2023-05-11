package silver3

import kotlin.math.min

// 탑다운 & 바텀업

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()
    var dp = IntArray(N + 1) { 987654321 }
    dp[N] = 0
    for (i in N downTo 1) {
        if (i % 2 == 0) {
            dp[i / 2] = min(dp[i] + 1, dp[i / 2])
        }
        if (i % 3 == 0) {
            dp[i / 3] = min(dp[i / 3], dp[i] + 1)
        }
        dp[i - 1] = min(dp[i - 1], dp[i] + 1)
    }

    println(dp[1])
}