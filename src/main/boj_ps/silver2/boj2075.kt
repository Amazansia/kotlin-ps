package silver2

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    var matrix = Array(N) { IntArray(N) }

    var answer: Triple<Int, Int, Int> = Triple(0, 0, 0)

    // matrix 정보 저장
    for (i in 0 until N) {
        matrix[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    // pq init
    // 값, Triple -> 값 기준 내림차순 정렬
    var pq = PriorityQueue<Triple<Int, Int, Int>> { o1, o2 -> o2.first - o1.first }
    repeat(N) {
        pq.add(Triple(matrix[N - 1][it], N - 1, it))
    }

    for (i in 1..N) {
        answer = pq.poll()
        if (answer.second != 0)
            pq.add(Triple(matrix[answer.second - 1][answer.third], answer.second - 1, answer.third))
    }

    print(answer.first)
}