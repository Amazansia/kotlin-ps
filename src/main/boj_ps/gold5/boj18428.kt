package gold5

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()
    var matrix = Array(N) { CharArray(N) }
    var answer = "NO"

    for (i in 0 until N) {
        var str = readLine().split(" ")
        for (j in 0 until N) {
            when (str[j]) {
                "X" -> matrix[i][j] = 'X'
                "T" -> matrix[i][j] = 'T'
                else -> matrix[i][j] = 'S'
            }
        }
    }

    fun isCaught(mat: Array<CharArray>): Boolean {

        var q: Queue<Pair<Int, Int>> = LinkedList()

        for (i in 0 until N) {
            for (j in 0 until N) {
                if (mat[i][j] == 'T') q.add(i to j)
            }
        }

        while (q.isNotEmpty()) {
            var now = q.poll()
            for (i in 1 until N) {
                var x = now.first - i
                if (x < 0) break
                if (mat[x][now.second] == 'O') break
                if (mat[x][now.second] == 'S') return true
            }
            for (i in 1 until N) {
                var x = now.first + i
                if (x >= N) break
                if (mat[x][now.second] == 'O') break
                if (mat[x][now.second] == 'S') return true
            }
            for (i in 1 until N) {
                var y = now.second - i
                if (y < 0) break
                if (mat[now.first][y] == 'O') break
                if (mat[now.first][y] == 'S') return true
            }
            for (i in 1 until N) {
                var y = now.second + i
                if (y >= N) break
                if (mat[now.first][y] == 'O') break
                if (mat[now.first][y] == 'S') return true
            }
        }
        return false
    }

    fun dfs(mat: Array<CharArray>, cnt: Int) {
        if (cnt > 3) return

        // 3개 세웠을 때 가장
        if (cnt == 3 && !isCaught(mat)) {
            answer = "YES"
            return
        }

        for (i in 0 until N) {
            for (j in 0 until N) {
                if (mat[i][j] == 'X') {

                    var newMat = Array(N) { CharArray(N) }
                    for (i in 0 until N) {
                        newMat[i] = mat[i].copyOf()
                    }
                    newMat[i][j] = 'O'
                    dfs(newMat, cnt + 1)
                }
            }
        }
    }

    dfs(matrix, 0)

    println(answer)
}