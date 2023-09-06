package silver1
/*
오름차순 수
오르막수의 개수를 10007로 나눈 나머지 출력
수는 0으로 시작할 수 있다
길이에 따라 동일한 수가 반복되어야 한다
마지막 수에 따라 다음에 올 수 있는 수의 개수가 달라진다
dp[i][j]: i의 길이를 가진 수면서 j로 끝나는 수의 개수
dp[1][0~9] = 1
ex) dp[2][3] = dp[1][0] + dp[1][1] + dp[1][2] + dp[1][3]
dp[2][4] = dp[1][0] + dp[1][1] + dp[1][2] + dp[1][3] + dp[1][4]
MOD 조심...
* */

fun main() = with(System.`in`.bufferedReader()) {
    var N = readln().toInt()
    val MOD = 10007
    var dp = Array(N + 1) { IntArray(10) }

    for (i in 0 until 10) {
        dp[1][i] = 1
    }

    for (i in 2..N) {
        for (j in 0..9) {
            for (k in 0..j) {
                // j = 5
                // 0 1 2 3 4 5
                dp[i][j] = (dp[i][j] + dp[i - 1][k]) % MOD
            }
        }
    }

    // dp[N][0] ~ dp[N][9]
    var answer = 0
    for (i in 0..9) {
        answer = (answer + dp[N][i]) % MOD
    }
    println(answer)
}