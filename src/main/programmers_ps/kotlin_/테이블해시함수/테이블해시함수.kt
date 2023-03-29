package kotlin_.테이블해시함수
/*
2차원 행렬
col번째 컬럼 값 기준으로 오름차순 정렬 && 첫 번째 컬럼 값 기준으로 내림차순 정렬
* */

class Solution {
    fun solution(data: Array<IntArray>, col: Int, row_begin: Int, row_end: Int): Int {
        var answer = 0

        // array 정렬
        data.sortWith(compareBy<IntArray> { it[col - 1] }.thenByDescending { it[0] })

        // Si: i번째 행에 대해 각 컬럼의 값을 i로 나눈 나머지들의 합
        // row_begin ~ row_end 인 모든 S_i XOR 연산 누적
        for (i in row_begin - 1 until row_end) {
            var sum = 0
            for (dt in data[i]) {
                sum += dt % (i + 1)
            }
            answer = answer.xor(sum)
        }

        return answer
    }
}