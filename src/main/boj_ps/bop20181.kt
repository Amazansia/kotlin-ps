import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
	var (N, K) = readLine().split(" ").map { it.toInt() }
	var preyArr = readLine().split(" ").map { it.toInt() }.toIntArray()
	/*
	N이 10만, NlogN 안에 뚫어야 함...
	sum == K -> 맨 처음 먹이를 뱉음
	sum < K -> 계속 먹음(값 갱신)
	sum > K -> stop, 최댓값 저장(sum - K) OR 맨 처음 먹이를 뱉고 다음 먹이를 먹을 수 있는지 확인
	dp[i] = 0 이상 i 미만의 먹이를 고려했을 때 모을 수 있는 최대 에너지
	투포인터 활용, i번째 칸에 도달했을 때 left = i, right = i, i는 움직이지 않음
	while(sum(left, right) < K) left-- -> while문 종료조건: sum(left, i) >= K
	dp[i] = max(dp[left - 1] + sum(left, i) - K, dp[i-1])
	*/

	var dp = LongArray(N + 1)

	var left = 0
	var right = 1
	var sum = preyArr[left]
	// dp[i] = 0 이상 i "미만"의 먹이를 고려했을 때 모을 수 있는 최대 에너지

	while (right <= N) {
		if (sum >= K) {
			while (sum >= K) {
				dp[right] = max(dp[right], dp[left] + sum - K)
				sum -= preyArr[left]
				left++
			}
		} else {
			dp[right] = max(dp[right], dp[right - 1])
			if (right == N) break
			sum += preyArr[right]
			right++
		}
	}
	println(dp[N])
}