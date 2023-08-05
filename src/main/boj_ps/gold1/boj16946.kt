package gold1

import java.util.*

/*
상하좌우로 맞닿아 있을 때 인접하다고 한다
벽을 부수고 이동할 수 있다면 그 위치에서 이동할 수 있는 칸의 개수는?
원래 빈칸이면 0
벽이라면 이동할 수 있는 칸의 개수를 10으로 나눈 나머지를 출력
뭔솔?
모두 탐색해야하나본데
0은 그냥 이동할 수 있는 칸이고
1은 해당 칸을 포함해서 이동가능한 칸의 개수를 알아야함 근데 벽을 더 부술 수는 없다
BFS
각 칸마다 이동가능한 칸의 개수를 dp로 저장해서 풀 수 있을까 -> 안됨 상하좌우 겹칠수있어서 중복될수도
그냥 쌩으로 탐색해야할듯...
* */

fun main() = with(System.`in`.bufferedReader()) {
    var (N, M) = readLine().split(" ").map { it.toInt() }
    var arr = Array(N) { IntArray(M) }
    for (i in 0 until N) {
        var str = readLine()
        for (j in 0 until M) {
            arr[i][j] = str[j].code - '0'.code
        }
    }

    var answer = Array(N) { IntArray(M) }

    val dxdy = arrayOf(1 to 0, 0 to 1, -1 to 0, 0 to -1)

    fun bfs(x: Int, y: Int): Int {
        var sum = 0
        arr[x][y] = 0

        var visited = Array(N) { BooleanArray(M) }

        var q: Queue<Pair<Int, Int>> = LinkedList()

        q.add(x to y)
        visited[x][y] = true

        while (q.isNotEmpty()) {
            sum++
            sum %= 10

            var now = q.poll()
            visited[now.first][now.second] = true

            for (i in dxdy) {
                var dx = now.first + i.first
                var dy = now.second + i.second

                if (dx !in 0 until N || dy !in 0 until M || visited[dx][dy] || arr[dx][dy] == 1) continue

                q.add(dx to dy)
                visited[dx][dy] = true
            }
        }

        arr[x][y] = 1
        return sum
    }

    for (i in 0 until N) {
        for (j in 0 until M) {
            answer[i][j] = if (arr[i][j] == 0) 0 else bfs(i, j)
        }
    }

    var sb = StringBuilder()

    for (i in 0 until N) {
        sb.append(answer[i].joinToString("") + "\n")
    }

    print(sb)
}
