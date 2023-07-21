package gold4

/*
길이 / 용량
파이프 용량의 최솟값이 수도관의 용량
총 길이가 정확히 D와 같게 할 때...
D가 10만이므로 배열 만들 수 있음
길이 오름차순으로 arr 정렬하고 dp로 만들 수 있는 모든 경우의 수를 저장하기?
같은 길이의 다른 용량을 가진 파이프는 저장할 필요가...있다
같은 길이 여러개를 써야 하는 단 하나의 정답이 존재할 경우도 생각해야 함
그럼 길이순 && 용량순 오름차순 정렬한다고 생각해보자
dp[i] = i의 길이를 가진 수도관을 설치했을 때 가능한 "최대" 수도관 용량
P는 350이다... 오
N으로 돌아도 문제없을듯
dp에 저장되어 있는 값이 0이 아닐 때, 해당 값과 현재 파이프 길이를 더한 dp[]에
최소파이프용량 저장하기

집합 A를 파이프 중 최적으로 고른 부분집합이라고 할 때
1. 집합 A가 N번째 파이프를 포함하고 있지 않다면,
A는 N번째 파이프를 뺀 나머지 N-1개의 파이프 중 최적으로 고른 부분집합과 같다
2. 집합 A가 N번째 파이프를 포함한다면,
A에 속한 최대 파이프 용량은 min(N-1개의 파이프 중 최적으로 고른 용량, N번째 파이프의 용량)이다.

dp[i][j]: i개의 파이프가 있고 길이가 j일 때 최대 용량

i번째 파이프의 길이가 D보다 크면 포함시킬 수 없다:
i번째 파이프를 뺀 i-1개의 파이프를 가지고 구한 전 단계의 최대 용량을 그대로 가져온다

D보다 작거나 같다면 포함시킬 수 있다:
i번째 파이프를 포함하여 전체 파이프 용량을 고려했을 때의 최대 용량
or i-1개의 파이프를 가지고 구한 전 단계의 최대 용량 중 큰 값을 선택한다.

뭔솔? 개어렵

booleanArray 써야할거같은데

 */

fun main() = with(System.`in`.bufferedReader()) {
    var (D, P) = readLine().split(" ").map { it.toInt() }
    // 파이프 정보 저장
    var pipes = Array(P) { 0 to 0 }
    var dp = Array(D + 1) { BooleanArray(P) }

    for (i in 0 until P) {
        pipes[i] = readLine().split(" ").map { it.toInt() }.let { it[0] to it[1] }
    }

    // booleanArray에서 합계 구하기
    fun BooleanArray.sum(): Int {
        var sum = 0
        forEachIndexed { idx, b -> if (b) sum += pipes[idx].first }
        return sum
    }

    fun BooleanArray.min(): Int {
        var answer = Int.MAX_VALUE
        forEachIndexed { idx, b -> if (b) answer = kotlin.math.min(answer, pipes[idx].second) }
        return answer
    }

    pipes.sortWith(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second })

    for (i in 0 until P) {
        dp[pipes[i].first][i] = true
    }

    for (i in 0 until P) {
        for (j in 0 until D + 1) {
            // dp[j]에서 i번째 파이프 쓰는지 체크, 안쓰면 진행
            if (dp[j][i]) continue
            // idx 범위체크
            if (D < pipes[i].first + dp[j].sum()) continue
            if (dp[j].sum() == 0) continue

            if (dp[j].min() <= pipes[i].second) {
                dp[j][i] = true
                dp[pipes[i].first + dp[j].sum()] = dp[j]
            }
        }
    }



    println(dp[D])
}