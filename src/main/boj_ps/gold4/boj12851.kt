package gold4

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (N, K) = readLine().split(" ").map { it.toInt() }

    val len = 100001
    var visited = BooleanArray(len) { false }
    var q: Queue<Pair<Int, Int>> = LinkedList()

    visited[N] = true
    q.add(Pair(N, 0))
    var time = 987654321
    var way = 0

    while (q.isNotEmpty()) {
        var front = q.poll()
        visited[front.first] = true

        // 불필요하게 멀리 가면 바로 종료
        if (time < front.second) {
            println(time)
            println(way)
            return
        }

        // N==K일 때
        if (front.first == K) {
            // depth가 같으면서 다른 루트일 경우
            if (time == front.second) {
                way++
                continue
            }
            // 최초로 time이 갱신되는 순간 == 가장 작은 depth의 방법을 찾아내는 순간
            if (time == 987654321) {
                time = front.second
                way++
                continue
            }
        }

        // 뒤로 걷기
        if (front.first - 1 >= 0 && !visited[front.first - 1]) {
            q.add(Pair(front.first - 1, front.second + 1))
        }
        // 앞으로 걷기
        if (front.first + 1 < len && !visited[front.first + 1]) {
            q.add(Pair(front.first + 1, front.second + 1))
        }
        if (front.first * 2 < len && !visited[front.first * 2]) {
            q.add(Pair(front.first * 2, front.second + 1))
        }
    }

    println(time)
    println(way)
}