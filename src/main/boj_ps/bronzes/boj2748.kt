package bronzes

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt() // <= 90

    var dp = LongArray(100)
    dp[0] = 0
    dp[1] = 1
    for (i in 2 until 100) {
        dp[i] = dp[i - 1] + dp[i - 2]
    }
    print(dp[N])
}