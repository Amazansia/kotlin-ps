import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()

    var paint = Array(N) { "" }
    var dxdy = arrayOf(1 to 0, 0 to 1, -1 to 0, 0 to -1)

    var no = 0
    var yes = 0
    var visited_no = Array(N) { BooleanArray(N) { false } }
    var visited_yes = Array(N) { BooleanArray(N) { false } }
    for (i in 0 until N) {
        paint[i] = readLine()
    }

    fun bfs_no(x: Int, y: Int, color: Char) {
        var q: Queue<Pair<Int, Int>> = LinkedList()
        q.add(Pair(x, y))
        visited_no[x][y] = true
        no++
        while (q.isNotEmpty()) {
            var front = q.poll()
            visited_no[front.first][front.second] = true

            for (i in dxdy) {
                var dx = i.first + front.first
                var dy = i.second + front.second
                if (dx < 0 || dy < 0 || dx >= N || dy >= N || visited_no[dx][dy] || paint[dx][dy] != color) {
                    continue
                }
                q.add(Pair(dx, dy))
                visited_no[dx][dy] = true
            }
        }
    }

    fun bfs_yes(x: Int, y: Int, color: Char) {
        var q: Queue<Pair<Int, Int>> = LinkedList()
        q.add(Pair(x, y))
        visited_yes[x][y] = true
        yes++
        while (q.isNotEmpty()) {
            var front = q.poll()
            visited_yes[front.first][front.second] = true

            for (i in dxdy) {
                var dx = i.first + front.first
                var dy = i.second + front.second
                if (dx < 0 || dy < 0 || dx >= N || dy >= N || visited_yes[dx][dy]) {
                    continue
                }
                if (color == 'R' || color == 'G') {
                    if (paint[dx][dy] == 'B') continue
                } else {
                    if (paint[dx][dy] != 'B') continue
                }
                q.add(Pair(dx, dy))
                visited_yes[dx][dy] = true
            }
        }
    }

    for (i in 0 until N) {
        for (j in 0 until N) {
            if (!visited_no[i][j]) {
                bfs_no(i, j, paint[i][j])
            }
            if (!visited_yes[i][j]) {
                bfs_yes(i, j, paint[i][j])
            }
        }
    }

    print("$no $yes")
}