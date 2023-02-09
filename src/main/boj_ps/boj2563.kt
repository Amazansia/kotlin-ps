fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()
    var dp = Array(100) { BooleanArray(100) { false } }
    repeat(N) {
        var (x, y) = readLine().split(" ").map { it.toInt() }
        for (i in x until x + 10) {
            for (j in y until y + 10) {
                dp[i][j] = true
            }
        }
    }

    var ans = 0
    for (i in 0 until 100) {
        for (j in 0 until 100) {
            if (dp[i][j]) {
                ans++
            }
        }
    }

    print(ans)
}