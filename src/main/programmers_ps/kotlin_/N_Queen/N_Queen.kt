package kotlin_.N_Queen

import kotlin.math.abs


class Solution {
    fun solution(n: Int): Int {
        var answer = 0

        var chess = IntArray(n) { -1 }

        fun check(x: Int, y: Int): Boolean {
            for (i in 0 until x) {
                if (chess[i] == y || abs(chess[i] - y) == abs(i - x))
                    return false
            }
            return true
        }

        fun backTracking(x: Int) {
            if (x == n) {
                answer++
                return
            }

            for (i in 0 until n) {
                if (check(x, i)) {
                    chess[x] = i
                    backTracking(x + 1)
                    chess[x] = -1
                }
            }
        }

        backTracking(0)

        return answer
    }
}