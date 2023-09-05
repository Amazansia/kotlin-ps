package gold2

import kotlin.math.*

/*
상어가 먹을 수 있는 물고기 번호의 합의 최댓값을 구하기오
4x4 공간으로
한 칸에는 물고기 한 마리씩
물고기는 번호와 방향이 있다: 번호 1~16
방향은 상하좌우+대각선 총 8가지
0, 0의 물고기를 "먹고" 시작한다
그다음 물고기가 이동한다
번호가 작은 물고기부터 순서대로 이동
물고기는 한 칸씩 이동: 이동가능칸 - 빈칸 or 다른 물고기가 있는 칸 / 불가칸 - 상어 or indexout
이동할 수 있을 때까지 45도씩 반시계 회전해본다
다돌아보고 이동이 안되면 안움직임
다른 물고기가 있는 칸으로 이동할때는 서로의 위치를 바꾸는 방식으로 이동한다.

물고기 이동이 모두 끝나면 상어가 이동한다: 한 번에 여러 칸을 이동 가능
물고기가 있는 칸으로 이동했다면 물고기를 먹고 그 물고기의 방향을 가지게 된다
이동 중 지나가는 칸의 물고기는 먹지 않는다
물고기가 없는 칸으로는 이동할 수 없다

fun 물고기이동() - 실행하면 물고기의 이동이 끝난다
fun 상어이동() - 상어가 한 번에 이동할 수 있는 칸의 최대 경우의 수는 3.
총 15개의 칸이 존재하므로 상어가 물고기를 모두 먹을 수 있는 경우가 존재한다면
최악의 시간복잡도는...
dfs를 사용해야 할까? 아니면 그리디? 아니면 dp?
매번 가능한 가장 큰 물고기를 먹는 방법이 항상 번호의 최댓값을 보장하는가? -> NO. 그리디 안됨
dfs로 구현해보기...
dp는 모든 변수의 상태를 저장해야 하기 때문에....

자료구조?
물고기 위치 및 방향 4x4에 저장해야 함...
물고기 번호순으로 보기: info[i][j][1]로 정렬하기
물고기 번호를 인덱스로 쓸건지 아니면 x,y를 인덱스로 쓸건지 정해야 한다
둘다 쓰면 동기화문제가 생길듯...
물고기 이동에는 번호와 x,y가 둘다 필요하고
상어 이동에는 x,y와 번호가 필요하다
dfs로 찾고자 하는 경우의 수: 상어가 잡아먹는 물고기의 위치
x,y를 인덱스로 쓰기!
info가 계속 변경되기 때문에 전역변수로 쓸 수 없고 인자로 넘겨야 함
* */
fun main() = with(System.`in`.bufferedReader()) {
	var info = Array(4) { Array(4) { IntArray(2) } }

	for (i in 0 until 4) {
		var str = readln().split(" ").map { it.toInt() }
		for (j in 0 until 4) {
			info[i][j] = intArrayOf(str[j * 2], str[j * 2 + 1])
		}
	}

	var answer = -1

	// x, y, direction
	var shark = Triple(0, 0, 0)

	// map의 물고기들이 한 차례 움직인 후의 map 정보를 반환
	fun moveFishes(map: Array<Array<IntArray>>): Array<Array<IntArray>> {
		return map
	}

	// 상어가 x, y로 움직였을 때 map 정보를 반환
	fun moveShark(map: Array<Array<IntArray>>, x: Int, y: Int): Array<Array<IntArray>> {
		map[x][y][0] = 0
		return map
	}

	// 상어의 방향에 따라 현재 맵에서 이동 가능한 위치를 x to y list로 반환
	fun possibleLocation(
		map: Array<Array<IntArray>>,
		shark: Pair<Int, Int>,
	): List<Pair<Int, Int>> {
		return listOf()
	}

	fun dfs(sum: Int, map: Array<Array<IntArray>>, shark: Pair<Int, Int>) {

		answer = max(answer, sum)

		var fishMovedMap = moveFishes(map)

		var lclist = possibleLocation(fishMovedMap, shark)
		for (i in lclist) {
			dfs(
				sum + fishMovedMap[i.first][i.second][0],
				moveShark(map, i.first, i.second),
				i
			)
		}
	}

	dfs(info[0][0][1], info, 0 to 0)
	println(answer)
}