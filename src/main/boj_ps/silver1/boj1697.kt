package silver1

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (N, K) = readLine().split(" ").map { it.toInt() }

    val len = 100001
    var visited = BooleanArray(len) { false }
    var q: Queue<Pair<Int, Int>> = LinkedList()

    visited[N] = true
    q.add(Pair(N, 0))
    var time = 0

    while (q.isNotEmpty()) {
        var front = q.poll()

        if (front.first == K) {
            time = front.second
            break
        }

        // 뒤로 걷기
        if (front.first - 1 >= 0 && !visited[front.first - 1]) {
            q.add(Pair(front.first - 1, front.second + 1))

            visited[front.first - 1] = true
        }
        // 앞으로 걷기
        if (front.first + 1 < len && !visited[front.first + 1]) {
            q.add(Pair(front.first + 1, front.second + 1))

            visited[front.first + 1] = true

        }
        if (front.first * 2 < len && !visited[front.first * 2]) {
            q.add(Pair(front.first * 2, front.second + 1))

            visited[front.first * 2] = true
        }
    }
    println(time)
}