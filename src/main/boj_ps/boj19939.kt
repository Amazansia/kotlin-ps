fun main() = with(System.`in`.bufferedReader()) {
	// n개의 공을 K개의 바구니에 나누어 담는데 각 바구니에 담긴 공의 개수는 모두 달라야 한다.
	// 가장 많이 담긴 바구니와 가장 적게 담긴 바구니의 공 개수 차이가 최소가 되어야 한다.
	// n/k로 나누고...
	/*
	fail이 나오는 조건: !(n>k(k-1)/2)
	n/k로 나누고 남은 나머지를 분배? 안됨...
	5 3 -> 1 2 2 fail
	6 3 -> 1 2 3 -> 2
	7 3 -> 1 2 4 -> 3
	8 3 -> 1 3 4 -> 3
	9 3 -> 2 3 4 -> 2
	10 3 -> 2 3 5 -> 3
	11 3 -> 2 4 5 -> 3
	* */
	var (N, K) = readLine().trim().split(" ").map { it.toInt() }
	if (N >= K * (K + 1) / 2) {
		var answer = K - 1
		if ((N - K * (K + 1) / 2) % K != 0) answer++
		print(answer)
	} else {
		print(-1)
	}
}