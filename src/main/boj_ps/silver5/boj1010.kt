package silver5

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    var dp = Array(31) { IntArray(31) }

    // 초기화
    for (i in 0..30) {
        dp[i][i] = 1
        dp[1][i] = i
    }

    fun rec(N: Int, M: Int): Int {
        if (dp[N][M] != 0) return dp[N][M]

        var sum = 0
        for (i in N - 1 until M) {
            sum += rec(N - 1, i)
        }
        dp[N][M] = sum
        return sum
    }

    repeat(N) {
        var (N, M) = readLine().split(" ").map { it.toInt() }
        println(rec(N, M))
    }
}