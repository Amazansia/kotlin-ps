package kotlin_.연속펄스부분수열의합

import kotlin.math.abs

/*
펄스 수열이란 1과 -1이 번갈아 나오는 수열이다
연속 펄스 부분 수열의 합 중 가장 큰 것을 return하시오
squence는 50만까지
내부의 원소는 +-10만
1로 시작하면 짝수번째 idx는 +, 홀수번째 idx는 -
-1로 시작하면 짝수번째 idx는 -, 홀수번째 idx는 +
500000 * 100000
50 000 000 000
저장되는 값이 int범위를 넘으므로 Long 사용해야 함
투포인터로 Long 변수 사용, 합계의 절댓값이 최대가 되는 순간이 정답
1로 시작한다고 가정, seqeunce의 홀수번째 idx 원소에 * -1해서 초기 저장
그럼 그냥 절댓값이 최대가 되는 순간의 부분수열을 구하면 끝...
* */

class Solution {
    fun solution(sequence: IntArray): Long {
        var answer: Long = 0

        // 배열 조작
        var sq = sequence.mapIndexed { index, it -> if (index % 2 == 1) it * -1 else it }.toIntArray()

        // 양수의 부분합
        // presum_positive[i]: arr[i]를 오른쪽 끝으로 하는 구간의 최대합
        var presumPositive = LongArray(sq.size)
        presumPositive[0] = sq[0].toLong()
        for (i in 1 until sq.size) {
            if (presumPositive[i - 1] + sq[i] > sq[i]) presumPositive[i] = presumPositive[i - 1] + sq[i]
            else presumPositive[i] = sq[i].toLong()
        }

        // 음수의 부분합
        // presumNositive[i]: arr[i]를 오른쪽 끝으로 하는 구간의 최소합
        var presumNegative = LongArray(sq.size)
        presumNegative[0] = sq[0].toLong()
        for (i in 1 until sq.size) {
            if (presumNegative[i - 1] + sq[i] > sq[i]) presumNegative[i] = sq[i].toLong()
            else presumNegative[i] = presumNegative[i - 1] + sq[i]
        }

        var sumPositive = presumPositive[0]
        presumPositive.forEach { if (it > sumPositive) sumPositive = it }
        var sumNegative = presumNegative[0]
        presumNegative.forEach { if (it < sumNegative) sumNegative = it }
        return kotlin.math.max(sumPositive, abs(sumNegative))
    }
}