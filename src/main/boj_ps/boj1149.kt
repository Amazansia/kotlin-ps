import java.lang.Math.min

/*
인접한 집의 색은 달라야 한다
빨 파 빨 초 빨 파 빨 파...
기본 DFS로 시간초과
DFS + 백트래킹 시간초과
DP...
dp[i][j] = i번째 집을 j로 칠했을 때의 최솟값
dp[i][j] = min(dp[i-1][!j]) + RGBCost[i][j]
* */

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()
    var RGBCost = Array(N + 1) { IntArray(3) }
    for (i in 1..N) {
        RGBCost[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    var dp = Array(N + 1) { IntArray(3) { Int.MAX_VALUE } }
    dp[1] = RGBCost[1]
    for (i in 2..N) {
        for (j in 0..2) {
            for (k in 0..2) {
                if (j == k) continue
                dp[i][j] = min(dp[i][j], dp[i - 1][k] + RGBCost[i][j])
            }
        }
    }
    var answer = Int.MAX_VALUE

    for (i in 0..2) {
        answer = min(answer, dp[N][i])
    }
    
//    fun dfs(nowIdx: Int, sum: Int, color: Int) {
//        if (nowIdx == N + 1) {
//            answer = answer.coerceAtMost(sum)
//            return
//        }
//        for (i in RGBCost[nowIdx].indices) {
//            if (i == color || sum + RGBCost[nowIdx][i] > answer) continue
//            dfs(nowIdx + 1, sum + RGBCost[nowIdx][i], i)
//        }
//    }
//
//    for (i in 0..2) {
//        dfs(1, 0, i)
//    }


    print(answer)
}