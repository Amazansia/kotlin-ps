package gold5

import java.util.*

// 1: 익은 토마토, 0: 안익은 토마토, -1: 토마토x

fun main() = with(System.`in`.bufferedReader()) {
    var (N, M) = readLine().split(" ").map { it.toInt() }
    var box = Array(M) { IntArray(N) }
    var visited = Array(M) { BooleanArray(N) }

    // 익은 토마토 위치 저장
    var q: Queue<Pair<Int, Int>> = LinkedList()
    var answer = 0

    repeat(M) { i ->
        box[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
        box[i].forEachIndexed { idx, it ->
            if (it == 1) {
                q.add(i to idx)
                visited[i][idx] = true
            } else if (it == -1) {
                visited[i][idx] = true
            }
        }
    }

    val dxdy = arrayOf(0 to 1, 1 to 0, 0 to -1, -1 to 0)

    // 하루동안 토마토를 익히는 메소드
    fun tomatoDaily(): Int {
        var count = 0
        var nowQ: Queue<Pair<Int, Int>> = LinkedList()
        while (q.isNotEmpty()) {
            var now = q.poll()
            for (i in 0 until 4) {
                var dx = now.first + dxdy[i].first
                var dy = now.second + dxdy[i].second

                if (dx !in 0 until M || dy !in 0 until N || visited[dx][dy] || box[dx][dy] != 0) {
                    continue
                }
                visited[dx][dy] = true
                box[dx][dy] = 1
                nowQ.add(dx to dy)
                count++
            }
        }
        q = nowQ
        return count
    }

    // 전체 칸 검사 메소드: 익지 않은 토마토가 있다면 true
    fun isNotRiped(): Boolean {
        box.forEach {
            if (it.contains(0)) return true
        }
        return false
    }

    while (true) {
        var count = tomatoDaily()
        if (count == 0) {
            println(if (isNotRiped()) -1 else answer)
            break
        }
        answer++
    }


}