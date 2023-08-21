package gold1
/*
누가봐도 dp
길이가 N이면서...
인접한 모든 자리의 차이가 1: 플러스마이너스 모두 가능
0부터 9까지 모두 등장하는 계단수
띠용 ㅋㅋ
진짜 개어려운데 왜 정답률 46%인거?????????다들 답본거지???????????그렇지???????????????

* */

fun main() = with(System.`in`.bufferedReader()) {
    var N = readln().toInt()
    val MOD = 1000000000
    var visit = 1 shl 10

    var dp = Array(101) { Array(10) { LongArray(visit) } }

    var sum = 0L

    for (i in 1 until 10) {
        dp[1][i][1 shl i] = 1
    }
    for (i in 2..N) {
        for (j in 0..9) {
            for (k in 0 until visit) {
                var bit = k or (1 shl j)
                dp[i][j][bit] =
                    (dp[i][j][bit] + ((if (0 < j) dp[i - 1][j - 1][k] else 0) + (if (j < 9) dp[i - 1][j + 1][k] else 0)) % MOD) % MOD
            }
        }
    }

    for (i in 0 until 10) sum = (sum + dp[N][i][visit - 1]) % MOD
    println(sum)
}