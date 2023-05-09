package silver3

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()

    val mod = 10007
    var dp = IntArray(N + 1)
    dp[1] = 1
    if (N < 2) {
        print(dp[N])
        return
    }
    dp[2] = 2

    if (N < 3) {
        print(dp[N])
        return
    }
    for (i in 3..N) {
        dp[i] = dp[i - 1] + dp[i - 2]
        dp[i] %= mod
    }
    print(dp[N])
}