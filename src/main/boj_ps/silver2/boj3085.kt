package silver2

import kotlin.math.max

/*
교환 횟수는 단 한 번
같은 색으로 이루어진 가장 긴 연속 부분의 사탕의 최대 개수
최대 연속 수열...
가로를 고려했을 때 가장 긴 수열
색마다 한번씩 4번 돌리면 될듯?
* */

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()
    var candies = Array(N) { "" }
    repeat(N) {
        candies[it] = readLine()
    }

    var answer = 0

    // x, y에서 오른쪽 or 아래 방향으로 고려했을 때 가장 많이 먹을 수 있는 사탕의 개수
    fun findMax(x: Int, y: Int): Int {
        var res = 0
        var color = candies[x][y]

        // 가로 오른쪽으로
        var dy = y
        var count = 0
        var isswapDone = false
        while (dy < N) {
            if (candies[x][dy] == color) {
                count++
            } else if (!isswapDone) {
                // 위아래에서 가져오는 경우
                if ((x - 1 in 0 until N && candies[x - 1][dy] == color)
                    || (x + 1 in 0 until N && candies[x + 1][dy] == color)
                ) {
                    count++
                    isswapDone = true
                }
                // 오른쪽에서 가져오는 경우
                else if (dy + 1 in 0 until N && candies[x][dy + 1] == color) {
                    count++
                    break
                } else break
            } else break
            dy++
        }
        res = max(res, count)

        // 가로 왼쪽으로
        dy = y
        count = 0
        isswapDone = false
        while (dy >= 0) {
            if (candies[x][dy] == color) {
                count++
            } else if (!isswapDone) {
                // 위아래에서 가져오는 경우

                if ((x - 1 in 0 until N && candies[x - 1][dy] == color)
                    || (x + 1 in 0 until N && candies[x + 1][dy] == color)
                ) {
                    count++
                    isswapDone = true
                }
                // 오른쪽에서 가져오는 경우
                else if (dy - 1 in 0 until N && candies[x][dy - 1] == color) {
                    count++
                    break
                } else break
            } else break
            dy--
        }
        res = max(res, count)

        // 세로 아래쪽으로
        var dx = x
        count = 0
        isswapDone = false
        while (dx < N) {
            if (candies[dx][y] == color) {
                count++
            } else if (!isswapDone) {
                // 양옆에서 가져오는 경우
                if ((y - 1 in 0 until N && candies[dx][y - 1] == color)
                    || (y + 1 in 0 until N && candies[dx][y + 1] == color)
                ) {
                    count++
                    isswapDone = true
                }
                // 아래에서 가져오는 경우
                else if (dx + 1 in 0 until N && candies[dx + 1][y] == color) {
                    count++
                    break
                } else break
            } else break
            dx++
        }
        res = max(res, count)

        dx = x
        count = 0
        isswapDone = false
        while (dx >= 0) {
            if (candies[dx][y] == color) {
                count++
            } else if (!isswapDone) {
                // 양옆에서 가져오는 경우
                if ((y - 1 in 0 until N && candies[dx][y - 1] == color)
                    || (y + 1 in 0 until N && candies[dx][y + 1] == color)
                ) {
                    count++
                    isswapDone = true
                }
                // 아래에서 가져오는 경우
                else if (dx - 1 in 0 until N && candies[dx - 1][y] == color) {
                    count++
                    break
                } else break
            } else break
            dx--
        }
        res = max(res, count)

        return res
    }

    fun isSame(c: Char): Boolean {
        for (i in 0 until N) {
            for (j in 0 until N) {
                if (candies[i][j] != c) return false
            }
        }
        return true
    }

    if (isSame('C') || isSame('P') || isSame('Y') || isSame('Z')) {
        println(0)
        return@with
    }

    for (i in 0 until N) {
        for (j in 0 until N) {
            answer = max(answer, findMax(i, j))
        }
    }

    println(answer)
}
