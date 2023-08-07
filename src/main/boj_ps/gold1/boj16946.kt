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

쌩탐색 시초 ㅎ
맨처음에 for돌면서
0이면 그 칸에서 출발해서 갈 수 있는 칸 개수 다 저장해버리기
mutableList<Pair<Int, Int>>로 한 덩어리로 묶이는 0의 집합 순서, 이동 가능한 칸 개수

그다음에 for돌면서 1이면 상하좌우 확인해서 0인 칸에서 출발해서 갈수있는칸 집합 인덱스로 접근해서 이동가능한 칸 개수 더해주기(0 until 4)
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

    var setlist = HashMap<Int, Int>()

    val dxdy = arrayOf(1 to 0, 0 to 1, -1 to 0, 0 to -1)

    var visited = Array(N) { BooleanArray(M) }

    var setidx = 20000

    // 0의 시작점을 받아 집합idx to bfs로 계산된 이동가능한 칸 개수를 map에 넣는 함수
    // 지나간 집합을 0 대신 인덱스 번호로 저장한다
    fun bfs(x: Int, y: Int) {
        var sum = 0

        var q: Queue<Pair<Int, Int>> = LinkedList()

        q.add(x to y)
        visited[x][y] = true
        arr[x][y] = setidx

        while (q.isNotEmpty()) {
            sum++
            sum %= 10

            var now = q.poll()
            arr[now.first][now.second] = setidx

            for (i in dxdy) {
                var dx = now.first + i.first
                var dy = now.second + i.second

                if (dx !in 0 until N || dy !in 0 until M || visited[dx][dy] || arr[dx][dy] == 1) continue

                q.add(dx to dy)
                visited[dx][dy] = true
            }
        }

        setlist[setidx++] = sum
    }

    for (i in 0 until N) {
        for (j in 0 until M) {
            if (arr[i][j] == 0 && !visited[i][j]) {
                bfs(i, j)
            }
        }
    }

    var sb = StringBuilder()

    fun getSumByLocation(x: Int, y: Int): Int {
        if (arr[x][y] != 1) return 0
        var sum = 1
        var tset = HashSet<Int>()
        for (i in dxdy) {
            var dx = x + i.first
            var dy = y + i.second
            if (dx !in 0 until N || dy !in 0 until M || arr[dx][dy] == 1 || tset.contains(arr[dx][dy])) continue
            tset.add(arr[dx][dy])
            sum += setlist[arr[dx][dy]] ?: 0
        }
        return sum % 10
    }

    for (i in 0 until N) {
        for (j in 0 until M) {
            sb.append(getSumByLocation(i, j))
        }
        sb.append("\n")
    }

    print(sb)
}
