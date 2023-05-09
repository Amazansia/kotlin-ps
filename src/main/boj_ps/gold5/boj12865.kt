package gold5

import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    var temp = readLine().split(" ")

    // 물품의 수
    val N = temp[0].toInt()
    // 버틸 수 있는 무게
    val K = temp[1].toInt()
    var dp = Array(N + 1) { IntArray(K + 1) }

    for (i in 1..N) {
        // weight, value
        val (w, v) = readLine().split(" ").map { it.toInt() }
        for (j in 1..K) {
            // 무게 제한을 벗어날 경우
            if (j < w) {
                dp[i][j] = dp[i - 1][j]
            }
            // 무게 제한을 벗어나지 않았을 경우
            else {
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - w] + v)
            }
        }
    }

    println(dp[N][K])
}
