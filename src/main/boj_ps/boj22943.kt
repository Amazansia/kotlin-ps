fun main() = with(System.`in`.bufferedReader()) {
	val (K, M) = readLine().split(" ").map { it.toInt() }
	// 0부터 9까지의 K가지의 숫자를 한 번씩만 사용하여 만들 수 있는 수 중 아래 조건을 만족하는 수들의 개수
	// 1. 서로 다른 두 개의 소수의 합으로 나타낼 수 있음
	// 2. M으로 나누어 떨어지지 않을 때까지 나눈 수가 두 개의 소수의 곱일 때. 이때 소수는 같아도 된다

	// K < 5 -> 최대 6, 100000, 소수 여부 검사
	var primenumarr = BooleanArray(100001) { true }
	primenumarr[0] = false
	primenumarr[1] = false
	for (i in 2..100000) {
		for (j in 2..100000) {
			if (i * j >= 100000) break
			if (i * j < 100000 && primenumarr[i * j]) {
				primenumarr[i * j] = false
			}
		}
	}

	var primeSum = BooleanArray(100000) { false }
	var primeMul = BooleanArray(100000) { false }

	// 소수의 합&곱을 계산
	for (i in 0 until 100000) {
		if (!primenumarr[i]) continue
		for (j in 0 until 100000) {
			if (!primenumarr[j]) continue
			var mul: Long = 1L * i * j
			if (mul < 100000) primeMul[i * j] = true
			if (i != j && i + j < 100000) {
				primeSum[i + j] = true
			}
		}
	}

	var visited = BooleanArray(10)
	var answer = 0

	fun isAnswer(num: Int): Boolean {
		if (!primeSum[num]) {
			return false
		}
		var n = num
		while (n % M == 0) n /= M
		if (!primeMul[n]) {
			return false
		}
		return true
	}

	fun bfs(current: Int, num: Int) {
		if (current == K) {
			if (isAnswer(num)) answer++
			return
		}
		for (i in 0..9) {
			if (i == 0 && current == 0 || visited[i]) continue
			visited[i] = true
			bfs(current + 1, num * 10 + i)
			visited[i] = false
		}
	}

	bfs(0, 0)
	print(answer)
}
