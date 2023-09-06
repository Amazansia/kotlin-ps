package gold4

import java.lang.Integer.min

/*
첫딜 9 두번째 3 세번째 1
체력 0 이하일 때 scv 파괴
N == 1이면 9로 나누고 나머지 존재할 경우 +1한 값이 정답
N == 2면 pq로 내림차순 정렬하고 9 3씩 딜넣어보면서 최솟값 구하기
N == 3이면...
내림차순 정렬한다고 무조건 모든 조건에 맞게 동작하는가?
10 9 1은?

60 * 60 * 60 = 216000 최대 이정도

10 9 1
1 6 0
0 0 0
1.
9 6 0
0 3 0
0 0 0
2.
1 9 1
0 0 0
내림차순 정렬로는 안된다
dfs 써야함
순서조합 6개 다 돌려봐야됨
즉 시복 6^N


3
60 0 0

51 57 59
50 54 50
45 47 49
40 44 44
35 41 39

32 36 34
31 27 33
28 26 24
19 23 23
18 20 14

11 15 13
10 6 10
1 5 9
0 2 0
-1 -7 -3
180 + 11
for문돌려서 바텀업하든가
재귀돌려서 탑다운하든가
탑다운이 맞는듯(음수체크때문에)
dp[i][j][k] : i,j,k의 체력을 가진 뮤탈리스크를 모두 파괴하는 데 필요한 최소횟수
dp[i+9][j+3][k+1] = min(dp[i+9][i+3][k+1], dp[i][j][k] + 1)
바꿔보면
dp[i][j][k] = min(dp[i-9][j-3][k-1]...(6가지 경우) + 1, dp[i][j][k])
* */

fun main() = with(System.`in`.bufferedReader()) {
    var N = readln().toInt()
    var arr = readLine().split(" ").map { it.toInt() }.toIntArray()

    var dp = Array(61) { Array(61) { IntArray(61) { 20 } } }

    fun lowerbound(n1: Int, n2: Int, n3: Int): Int {
        var i = if (n1 < 0) 0 else n1
        var j = if (n2 < 0) 0 else n2
        var k = if (n3 < 0) 0 else n3

        return dp[i][j][k]
    }

    dp[0][0][0] = 0

    for (i in 0 until 61) {
        for (j in 0 until 61) {
            for (k in 0 until 61) {
                dp[i][j][k] = min(lowerbound(i - 9, j - 3, k - 1) + 1, dp[i][j][k])
                dp[i][j][k] = min(lowerbound(i - 9, j - 1, k - 3) + 1, dp[i][j][k])
                dp[i][j][k] = min(lowerbound(i - 3, j - 9, k - 1) + 1, dp[i][j][k])
                dp[i][j][k] = min(lowerbound(i - 3, j - 1, k - 9) + 1, dp[i][j][k])
                dp[i][j][k] = min(lowerbound(i - 1, j - 9, k - 3) + 1, dp[i][j][k])
                dp[i][j][k] = min(lowerbound(i - 1, j - 3, k - 9) + 1, dp[i][j][k])
            }
        }
    }

    println(dp[arr[0]][if (arr.size >= 2) arr[1] else 0][if (arr.size >= 3) arr[2] else 0])
}