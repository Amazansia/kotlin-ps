//package kotlin_.퍼즐조각채우기
/*
1. 테이블에서 어떻게 퍼즐을 추출할 것인가
2. 게임보드에서 빈칸을 어떻게 추출할 것인가
3. 퍼즐을 어떻게 회전할 것인가
4. 게임보드에 이 회전되는 퍼즐을 어떻게 맞출 것인가?
* */

class Solution {
    val dx = intArrayOf(0, 0, 1, 0, -1)
    val dy = intArrayOf(0, 1, 0, -1, 0)
    fun solution(game_board: Array<IntArray>, table: Array<IntArray>): Int {
        var answer = 0
        var puzzles = mutableListOf<MutableList<Pair<Int, Int>>>()

        // dfs로 퍼즐 정보를 찾아내는 함수
        fun dfs(x: Int, y: Int, puzzle: MutableList<Pair<Int, Int>>, visited: Array<BooleanArray>) {
            for (i in 0 until 5) {
                var xx = dx[i] + x
                var yy = dy[i] + y
                // 범위검사
                if (xx !in table.indices || yy !in 0 until table[0].size) {
                    continue
                }
                if (table[xx][yy] == 1 && !visited[xx][yy]) {
                    // 0,0 0,1 1,1 2,1 2,2
                    puzzle.add(xx to yy)
                    visited[xx][yy] = true
                    dfs(xx, yy, puzzle, visited)
                }
            }
        }

        // dfs로 퍼즐 정보를 찾아내는 함수
        fun dfs_blank(x: Int, y: Int, puzzle: MutableList<Pair<Int, Int>>, visited: Array<BooleanArray>) {
            for (i in 0 until 5) {
                var xx = dx[i] + x
                var yy = dy[i] + y
                // 범위검사
                if (xx !in table.indices || yy !in 0 until table[0].size) {
                    continue
                }
                if (game_board[xx][yy] == 0 && !visited[xx][yy]) {
                    puzzle.add(xx to yy)
                    visited[xx][yy] = true
                    dfs_blank(xx, yy, puzzle, visited)
                }
            }
        }

        // 퍼즐 정보를 저장해서 리턴하는 함수
        var visited = Array(table.size) { BooleanArray(table[0].size) }
        fun findPuzzles() {
            for (i in table.indices) {
                for (j in 0 until table[0].size) {
                    if (table[i][j] == 1 && !visited[i][j]) {
                        var puzzle = mutableListOf<Pair<Int, Int>>()
                        dfs(i, j, puzzle, visited)
                        if (puzzle.isNotEmpty()) {
                            puzzle = puzzle.map { it.first - i to it.second - j }.toMutableList()
                            puzzle.sortWith(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second })
                            puzzles.add(puzzle)
                        }
                    }
                }
            }
        }

        fun matchWithRotation(b: MutableList<Pair<Int, Int>>): Boolean {
            // 퍼즐은 0,0 원점으로 초기화해서 저장했었다
            // 8,1 8,2 9,1 9,2 9,3
            // 0,0 0,1 1,0 1,1 1,2
            var bFirst = b.first()
            var newBlank = b.map { it.first - bFirst.first to it.second - bFirst.second }
            puzzles.filter { it.size == b.size }.forEach { puzzle ->
                // 소트 안해도 될듯
                var np = puzzle.sortedWith(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second })
                var rot = 0

                while (rot < 4) {
                    if (newBlank == np) {
                        puzzles.remove(puzzle)
//                        println(puzzles.size)
//                        println(puzzles.joinToString("\n"))
                        return true
                    }

                    np = np.map { it.second to -1 * it.first }
                        .sortedWith(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second })
                    var npFirst = np.first()
                    // -1,-1 , 0,0
                    // 0,0 1,1
                    np = np.map { it.first - npFirst.first to it.second - npFirst.second }
                    rot++
                }
            }
            return false
        }

        fun putPuzzles() {
            visited.forEach { it.fill(false) }
            for (i in table.indices) {
                for (j in 0 until table[0].size) {
                    if (game_board[i][j] == 0 && !visited[i][j]) {
                        var blank = mutableListOf<Pair<Int, Int>>()
                        dfs_blank(i, j, blank, visited)
                        if (blank.isNotEmpty()) {
                            blank.sortWith(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second })
//                            println("blank: ${blank.joinToString(" ")}")
                            if (matchWithRotation(blank)) {
                                answer += blank.size
//                            } else {
//                                println("match failed Blank: ${blank.joinToString(" ")}")
//                            }
//                            println(puzzles.size)
                            }
                        }
                    } else if (game_board[i][j] == 1)
                        visited[i][j] = true
                }
            }
        }

        findPuzzles()
        putPuzzles()

//        visited.forEach { println(it.joinToString { t -> if (t) "1" else "0" }) }

        return answer
    }
}