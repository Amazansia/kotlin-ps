//package kotlin_.퍼즐조각채우기

/*
빈칸에 딱 맞아야 함
게임보드에서 원래 막힌 칸은 1, 퍼즐로 채워지는 칸은 2, 빈칸은 0
테이블에 놓인 퍼즐의 정보?
퍼즐을 회전하는 게 아니라 게임테이블을 회전시킨다면?
한번 회전시킬 경우 (x, y) -> (y, N-x)로 변환됨
퍼즐 정보 저장: mutablelist
* */
class Solution {
	val dx = intArrayOf(0, 1, 0, -1)
	val dy = intArrayOf(1, 0, -1, 0)
	fun solution(game_board: Array<IntArray>, table: Array<IntArray>): Int {
		var answer: Int = 0
		var puzzles = mutableListOf<MutableList<Pair<Int, Int>>>()

		// dfs로 퍼즐 정보를 찾아내는 함수
		fun dfs(x: Int, y: Int, puzzle: MutableList<Pair<Int, Int>>, visited: Array<BooleanArray>) {
			for (i in 0 until 4) {
				var xx = dx[i] + x
				var yy = dy[i] + y
				// 범위검사
				if (xx !in 0 until table.size || yy !in 0 until table[0].size) {
					continue
				}
				if (table[xx][yy] == 1 && !visited[xx][yy]) {
					puzzle.add(xx to yy)
					visited[xx][yy] = true
					dfs(xx, yy, puzzle, visited)
					// 이줄 있어야하나?
//					visited[xx][yy] = false
				}
			}
		}
		// dfs로 퍼즐 정보를 찾아내는 함수
		fun dfs_blank(x: Int, y: Int, puzzle: MutableList<Pair<Int, Int>>, visited: Array<BooleanArray>) {
			for (i in 0 until 4) {
				var xx = dx[i] + x
				var yy = dy[i] + y
				// 범위검사
				if (xx !in 0 until table.size || yy !in 0 until table[0].size) {
					continue
				}
				if (game_board[xx][yy] == 0 && !visited[xx][yy]) {
					puzzle.add(xx to yy)
					visited[xx][yy] = true
					dfs_blank(xx, yy, puzzle, visited)
					// 이줄 있어야하나?
//					visited[xx][yy] = false
				}
			}
		}

		// 퍼즐 정보를 저장해서 리턴하는 함수
		var visited = Array(table.size) { BooleanArray(table[0].size) }
		fun findPuzzles() {
			for (i in 0 until table.size) {
				for (j in 0 until table[0].size) {
					if (table[i][j] == 1) {
						var puzzle = mutableListOf<Pair<Int, Int>>()
//						puzzle.add(i to j)
						dfs(i, j, puzzle, visited)
						// [4,1] [4,2] [5,2] -> [0,0] [0,1] [1,1]
//						var xMax = puzzle.maxOf { it.first }
//						var yMax = puzzle.maxOf { it.second }
						if(puzzle.isNotEmpty()){
							puzzle = puzzle.map { it.first - i to it.second - j }.toMutableList()
							puzzle.sortWith(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second })
							puzzles.add(puzzle)
						}
					}
				}
			}
		}

		fun matchWithRotation(b: MutableList<Pair<Int, Int>>): Boolean {
			puzzles.filter { it.size == b.size }.forEach { puzzle ->
				var np =
					puzzle.sortedWith(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second })
				var rot = 0
				while (rot < 4) {
//					var xMax = puzzle.maxOf { it.first }
//					var yMax = puzzle.maxOf { it.second }
					for (i in b.indices) {
						var dx = b[0].first + np[i].first
						var dy = b[0].second + np[i].second
						// 범위를 벗어나면
						if (dx !in 0 until table.size || dy !in 0 until table[0].size) {
							break
						}
						if (dx == b[i].first && dy == b[i].second && i == b.size - 1) {
//							println(b.joinToString(" "))
							puzzles.remove(puzzle)
							return true
						}
					}
					np = np.map { it.second to -1 * it.first }.sortedWith(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second })
					rot++
				}
			}
			return false
		}

//		println(visited.joinToString(" "))
		fun putPuzzles() {
			visited.forEach { it.fill(false) }

			for (i in 0 until table.size) {
				for (j in 0 until table[0].size) {
					if (game_board[i][j] == 0 && !visited[i][j]) {
						var blank = mutableListOf<Pair<Int, Int>>()
//						blank.add(i to j)
						dfs_blank(i, j, blank, visited)
						if(blank.isNotEmpty()){
							blank.sortWith(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second })
//							println("blank: ${blank.joinToString(" ")}")
							if (matchWithRotation(blank)) {
								answer += blank.size
							}
						}
					}
				}
			}
		}

		findPuzzles()
//		println("")
//		println(puzzles.joinToString(" "))
		putPuzzles()

		return answer
	}
}