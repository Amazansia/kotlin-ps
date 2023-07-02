package gold4

import kotlin.system.exitProcess

/*
스도쿠 판을 채워봐라
빈 칸은 0으로 주어진다.
스도쿠 판을 채우는 방법이 여럿이면 그 중 하나를 출력하면 된다.
빈칸 위치를 저장해놓고 해당 위치에 들어갈 수 있는 수를 booleanarray에 저장한다.
mutablelist<Triple<x:Int, y:Int, check: BooleanArray>>
모든 경우의 수를 순회하며 완성할 수 있는지 본다?
스도쿠는 array로 저장
dfs로... 브루트포스?
빈칸갯수에 따라 시간복잡도가 달라질듯
빈칸에 들어갈 수 있는 경우의 수가 확정되면(경우의 수를 저장하는 linkedlist의 길이가 1이 되면) arr에 저장(확정)
1. 빈칸을 순회하며 가능한 경우의 수를 booleanArray(10)에 저장한다.
	1-1. 빈칸에서 세 가지 경우의 수(가로, 세로, 3x3 정사각형)를 고려했을 때 가능한 경우의 수가 한 가지라면 arr에 저장한다
	1-2. 경우의 수가 여러 가지인 빈칸이라면 list에서 제거하지 않고 다시 돌린다
1번을 while(list.isnotempty()) 로 돌린다

위 풀이의 경우 여러 가지 경우의 수를 한번에 고려해야 하는 경우는 데드락이 발생하므로 무한루프가 돌게 된다.
확정할 수 있는 경우는 확정하고, 데드락이 발생하는 경우는 dfs로 bf 고려해줘야 함.
시간초과...

dfs+벡트래킹(가지치기)
들어갈 수 있는 경우의 수를 dfs로 고려한다
* */

fun main() = with(System.`in`.bufferedReader()) {
    var board = Array(9) { IntArray(9) }
    for (i in 0 until 9) {
        board[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    var list = mutableListOf<Triple<Int, Int, BooleanArray>>()

    for (i in 0 until 9) {
        for (j in 0 until 9) {
            if (board[i][j] == 0) list.add(Triple(i, j, BooleanArray(10) { it != 0 }))
        }
    }

    fun check(x: Int, y: Int, n: Int): Boolean {
        // 가로 || 세로
        for (i in 0 until 9) {
            if (board[x][i] == n || board[i][y] == n) return false
        }

        // 3x3 정사각형
        for (i in x / 3 * 3..x / 3 * 3 + 2) {
            for (j in y / 3 * 3..y / 3 * 3 + 2) {
                if (board[i][j] == n) return false
            }
        }

        return true
    }

    fun backtracking(cnt: Int) {

        if (cnt == list.size) {
            board.forEach {
                println(it.joinToString(" "))
            }
            exitProcess(0)
        }

        for (i in 1..9) {
            if (check(list[cnt].first, list[cnt].second, i)) {
                // 채우기 시도
                board[list[cnt].first][list[cnt].second] = i
                // 일단 가보기
                backtracking(cnt + 1)
                // 안되면 돌아와서 초기화
                board[list[cnt].first][list[cnt].second] = 0
            }
        }
    }

    backtracking(0)
}