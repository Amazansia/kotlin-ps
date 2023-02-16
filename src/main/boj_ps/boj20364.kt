fun main() = with(System.`in`.bufferedReader()) {
	var (N, Q) = readLine().split(" ").map { it.toInt() }
	var duckNeedsArray = IntArray(N + 1)
	repeat(Q) {
		duckNeedsArray[it + 1] = readLine().toInt()
	}
	// 땅 번호 보니까 힙인 것 같아용... -> 완전이진트리, 배열로 저장
	// 땅 ok -> 0, 안되면 처음 마주치는 점유된 땅 번호
	// 점유 순서는 입력받은 순서대로
	// 가는 경로상에 점유된 땅이 있다면... -> N이 2^20이므로 Int 내에서 ok,
	// 저장 형식이 좀 헷갈리는...
}