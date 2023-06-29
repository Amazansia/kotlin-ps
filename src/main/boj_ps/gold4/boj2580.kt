package gold4/*
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

//	for (i in 0 until 9) {
//		for (j in 0 until 9) {
//			if (board[i][j] == 0)
//			// arr[0] = false, 나머지 true
//				list.add(Triple(i, j, BooleanArray(10) { it != 0 }))
//		}
//	}

	fun collectPossibleNum(now: Triple<Int, Int, BooleanArray>, bArr: Array<IntArray>) {
		// 가로 || 세로
		for (i in 0 until 9) {
			now.third[bArr[i][now.second]] = false
		}

		for (i in 0 until 9) {
			now.third[bArr[now.first][i]] = false
		}

		// 3x3 정사각형
		for (i in 0..2) {
			for (j in 0..2) {
				var dx = now.first / 3 * 3 + i
				var dy = now.second / 3 * 3 + j
				now.third[bArr[dx][dy]] = false
			}
		}

		now.third[0] = false
	}

	// booleanArray에서 가능한 수는 true, 불가능한 수는 false
//	while (list.size > 0) {
//		var removelist = mutableListOf<Triple<Int, Int, BooleanArray>>()
//		for (i in list) {
//			i.third.fill(true)
//			i.third[0] = false
//			collectPossibleNum(i)
//			if (i.third.count { it } == 1) {
//				removelist.add(i)
//			}
//		}
//		removelist.forEach {
//			list.remove(it)
//			board[it.first][it.second] = it.third.lastIndexOf(true)
//		}
//	}

	fun isAllDone(arr: Array<IntArray>): Boolean {
		for (i in 0 until 9) {
			for (j in 0 until 9) {
				if (arr[i][j] == 0) return false
			}
		}
		return true
	}

	fun backtracking(now: Triple<Int, Int, BooleanArray>, bArr: Array<IntArray>) {

		println("backtracking: ${now.first}, ${now.second}")
		// 기저조건: 스도쿠 완성
		if (isAllDone(bArr)) {
			bArr.forEach {
				println(it.joinToString(" "))
			}
			return
		}

		// now.first, now.second에서 가능한 숫자 정보 now.third에 저장
		collectPossibleNum(now, bArr)

		for (i in now.third.indices) {
			// true면 가능한 수, false면 안됨
			if (now.third[i]) {
				bArr[now.first][now.second] = now.third.indexOf(true)
				// 되는 경우로 다음 칸 채우기 시도
				for (i in now.first until 9) {
					for (j in now.second until 9) {
						if (bArr[i][j] == 0) {
							backtracking(Triple(i, j, BooleanArray(10) { it != 0 }), bArr)
							// 안되면 초기화
							bArr[now.first][now.second] = 0
							now.third[i] = false
							break
						}
					}
				}
			}
		}
	}

	for (i in 0 until 9) {
		for (j in 0 until 9) {
			if (board[i][j] == 0) {
				backtracking(Triple(i, j, BooleanArray(10) { it != 0 }), board)
			}
		}
	}

}