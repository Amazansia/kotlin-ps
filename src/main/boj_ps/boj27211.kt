import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(" ").map { it.toInt() }
    var planet = Array(N) { BooleanArray(M) }
    for (i in 0 until N) {
        planet[i] = readLine().split(" ").map { it == "1" }.toBooleanArray()
    }

    var answer = 0
    // 상하좌우 움직이기
    // 0(false): 이동가능, 1(true): 숲으로 막혀 있음
    var dxdy = arrayOf(0 to 1, 1 to 0, 0 to -1, -1 to 0)

    fun bfs(i: Int, j: Int) {
        answer++
        var q: Queue<Pair<Int, Int>> = LinkedList()
        q.add(Pair(i, j))
        planet[i][j] = true

        while (q.isNotEmpty()) {
            var front = q.poll()
            planet[front.first][front.second] = true
            for (it in dxdy) {
                var dx = (front.first + it.first + N) % N
                var dy = (front.second + it.second + M) % M

                if (!planet[dx][dy]) {
                    q.add(Pair(dx, dy))
                    planet[dx][dy] = true
                }
            }
        }
    }

    for (i in 0 until N) {
        for (j in 0 until M) {
            if (!planet[i][j]) {
                bfs(i, j)
            }
        }
    }

    print(answer)
}