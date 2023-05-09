package silver3

import kotlin.math.min
import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()

    var dp = IntArray(N + 1) { 0 }
    var end = sqrt(N.toDouble()).toInt()
    for (i in 1..end) {
        for (j in 1..3) {
            if (i * i * j > N) break
            dp[i * i * j] = j
        }
    }

    for (i in 1..N) {
        if (dp[i] != 0) continue
        var sqrt_i = sqrt(i.toDouble()).toInt()
        for (j in 1 until sqrt_i) {
            dp[i] = min(1 + dp[i - j * j], if (dp[i] != 0) dp[i] else 5)
        }
    }

    println(dp[N])
}