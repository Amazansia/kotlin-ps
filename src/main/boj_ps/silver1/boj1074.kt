package silver1

import kotlin.math.pow

fun main() = with(System.`in`.bufferedReader()) {
    var answer = 0
    var (N, R, C) = readLine().split(" ").map { it.toInt() }

    var size = 2.toDouble().pow(N).toInt()

    fun solution(size: Int, r: Int, c: Int) {
        if (size == 0) return
        if (r < size / 2 && c < size / 2) {
            solution(size / 2, r, c)
        } else if (r < size / 2 && c >= size / 2) {
            answer += size * size / 4
            solution(size / 2, r, c - size / 2)
        } else if (r >= size / 2 && c < size / 2) {
            answer += size * size / 2
            solution(size / 2, r - size / 2, c)
        } else {
            answer += size * size / 4 * 3
            solution(size / 2, r - size / 2, c - size / 2)
        }
    }

    solution(size, R, C)
    print(answer)
}