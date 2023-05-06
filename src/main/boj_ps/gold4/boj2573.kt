package gold4

import java.util.*

// 빙산의 높이는 일년마다 동서남북 네 방향으로 붙어있는 0이 저장된 칸의 개수만큼 줄어든다
// 단, 0보다 더 줄어들지 않는다
// 두 덩어리 이상으로 분리되는 최초의 시점을 구하여라
// N M 300 이하
// 빙산은 10000개 이하
// 시뮬 + BFS
fun main() = with(System.`in`.bufferedReader()) {
    var (N, M) = readLine().split(" ").map { it.toInt() }
    var sea = Array(N) { IntArray(M) }
    var answer = 0
    val dxdy = arrayOf(0 to 1, 1 to 0, 0 to -1, -1 to 0)

    // 얼음 위치 저장하는 q
    var ices: Queue<Triple<Int, Int, Int>> = LinkedList()

    var visited = Array(N) { BooleanArray(M) { true } }

    repeat(N) { i ->
        sea[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
        sea[i].forEachIndexed { j, it ->
            if (it != 0) {
                ices.add(Triple(i, j, it))
                visited[i][j] = false
            }
        }
    }

    fun isIceDivided(): Boolean {
        if (ices.isEmpty()) return false
        var q: Queue<Triple<Int, Int, Int>> = LinkedList()
        var now = ices.peek()
        var vv = Array(N) { BooleanArray(M) }
        vv[now.first][now.second] = true
        var count = 0
        q.add(now)

        while (q.isNotEmpty()) {
            now = q.poll()
            count++
            for (i in 0 until 4) {
                var dx = now.first + dxdy[i].first
                var dy = now.second + dxdy[i].second

                if (dx !in 0 until N || dy !in 0 until M || sea[dx][dy] == 0 || vv[dx][dy]) {
                    continue
                }
                q.add(Triple(dx, dy, 0))
                vv[dx][dy] = true
            }
        }

        return ices.size != count
    }

    fun countZero(x: Int, y: Int): Int {
        var count = 0
        for (i in 0 until 4) {
            var dx = x + dxdy[i].first
            var dy = y + dxdy[i].second

            if (dx !in 0 until N || dy !in 0 until M) {
                continue
            }
            if (sea[dx][dy] == 0)
                count++
        }

        return count
    }

    fun iceMelting() {
        var meltingNumArr = Array(N) { IntArray(M) }

        for (i in ices) {
            meltingNumArr[i.first][i.second] = countZero(i.first, i.second)
        }

        var newIces: Queue<Triple<Int, Int, Int>> = LinkedList()
        while (ices.isNotEmpty()) {
            var now = ices.poll()
            var res = now.third - meltingNumArr[now.first][now.second]
            if (res > 0) {
                sea[now.first][now.second] = res
                newIces.add(Triple(now.first, now.second, res))
            } else {
                sea[now.first][now.second] = 0
            }
        }
        ices = newIces
    }

    while (ices.isNotEmpty()) {
        answer++
        iceMelting()
        if (isIceDivided()) {
            println(answer)
            return@with
        }
    }

    println(0)
}