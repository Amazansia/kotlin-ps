package silver3

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    var arr = IntArray(300)
    repeat(N) {
        arr[it] = readLine().toInt()
    }

    var dp = IntArray(300)
    dp[0] = arr[0]
    dp[1] = arr[0] + arr[1]
    dp[2] = kotlin.math.max(arr[0] + arr[2], arr[1] + arr[2])
    for (i in 3 until N) {
        dp[i] = kotlin.math.max(dp[i - 2] + arr[i], dp[i - 3] + arr[i - 1] + arr[i])
    }

    print(dp[N - 1])

}