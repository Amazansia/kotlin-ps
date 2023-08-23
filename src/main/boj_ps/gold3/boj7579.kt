package gold3
/*
비활성화하고 재실행할 때 사용되는 비용이 C
M바이트 이상의 메모리 확보...
근데 이제 비용 C를 최소화하는 방법을 찾아서
배낭 아니면 dp
배낭이 dp아닌가요?
배낭이 더 어려우니까 구분해줍시다
처음이면 j가 c 이상이면 값 담고
처음이 아니면 최대값 계산
1. 이전행에서 c 안쓰고 확보한 메모리값 + 이번에 c 쓰고 확보한 메모리
2. 이전행에서 같은 비용 써서 확보한 메모리
* */

fun main() = with(System.`in`.bufferedReader()) {
    var (N, M) = readln().split(" ").map { it.toInt() }
    var A = readln().split(" ").map { it.toInt() }.toIntArray()
    var C = readln().split(" ").map { it.toInt() }.toIntArray()

    var answer = Int.MAX_VALUE
    var dp = Array(N) { IntArray(100001) }
    for (i in 0 until N) {
        var c = C[i]
        var mem = A[i]

        for (j in 0..100000) {
            if (i == 0) {
                if (j >= c) dp[i][j] = mem
            } else {
                if (j >= c) dp[i][j] = Math.max(dp[i - 1][j - c] + mem, dp[i - 1][j])
                else dp[i][j] = dp[i - 1][j]
            }
            if (dp[i][j] >= M) answer = Math.min(answer, j)
        }
    }

    println(answer)

}