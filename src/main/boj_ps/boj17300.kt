/*
boolArray[10]으로 저장
이동 경로 체크
1 5 9 8 7 3 -> 가능
3 4 가능? -> 불가능

1. 목적지가 인접하는가? (3, 4는 인접하지 않고, 1, 6또한 인접하지 않는다)
123
456
789
2. 목적지가 인접하지 않는다면, 목적지까지의 경로에 하나 이상의 점이 존재하며 방문을 완료했는가?
둘 중 하나가 ok라면 이동 가능
* */
fun main() = with(System.`in`.bufferedReader()) {
	var L = readLine().trim().toInt()
	var pattern = readLine().trim().split(" ").map { it.toInt() }.toIntArray()
	var answer = "YES"
	// 상하좌우 && 대각선
	val move = arrayOf(0 to 1, 1 to 0, -1 to 0, 0 to -1, 1 to 1, -1 to -1, 1 to -1, -1 to 1)
	var visited = BooleanArray(10)
	var numPad = arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9))

	// 1 2 3
	//[0][0] [0][1] [0][2]
	//[1][0] [1][1] [1][2]
	fun isOkay(start: Int, end: Int): Boolean {
		for (i in move) {
			// dxdy는 numpad상에서의 위치좌표
			var dx = i.first + (start - 1) / 3
			var dy = i.second + (start - 1) % 3
			if (dx in 0 until 3 && dy in 0 until 3) {
				if (numPad[dx][dy] == end) return true
				if(visited[numPad[dx][dy]] && isOkay(numPad[dx][dy], end)){
					return true
				}
			}
		}
		return false
	}
	visited[pattern.first()] = true
	for (i in 0 until L - 1) {
		if (visited[pattern[i + 1]] || !isOkay(pattern[i], pattern[i + 1])) {
			answer = "NO"
			break
		} else {
			visited[pattern[i + 1]] = true
		}
	}
	print(answer)
}