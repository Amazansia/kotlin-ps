import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
	var N = readLine().toInt()
	var arr = Array(N + 1) { IntArray(2) { 0 } }
	for (i in 1..N) {
		arr[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
	}

	/*
	1차 시도: P/T 나누기로 우선순위를 정해서?
	1. toInt()로 소수점을 버리지 않고 Double과 Float형으로 비교하더라도, P/T의 값이 충분히 정확하지 않을 수 있다
	일정 이하의 소수점에서 완벽한 순위 보장이 불가능하다.
	2. P/T가 모두 동일할 때(예제입력 4 참고), 우선순위를 정할 방법이 없다. <- 뒤에서부터 계산하면 되지 않을까 하는 생각을 조금?
	2차 시도: DP - dp[i]: i일까지 상담을 수행했을 때 받을 수 있는 수임료의 최댓값.
	i일까지의 최대 상담료는 점화식을 이용해서 풀 수 있을 듯 싶은데, 이때 for문을 사용해야 할 듯 하다...
	그러나 최대 time이 50이므로 for문이 최대 50번 수행되는 범위 내에서 max값을 구할 수 있을 듯.
	이렇게 되면 N 150만 * T 50 으로 7500만 -> 1초 안에 통과 가능할 듯한?
	*/
	var dp = IntArray(N + 1)
	var temp = 0

	for (i in 1..N) {
		var endDay = i + arr[i][0] - 1

		if (endDay > N) {
			dp[i] = temp
			continue
		}
		if (dp[endDay] < dp[i - 1] + arr[i][1]) {
			dp[endDay] = dp[i - 1] + arr[i][1]
			temp = max(dp[endDay], temp)

			for (j in i until endDay) {
				if (j > N) break
				dp[j] = max(dp[i - 1], dp[j])
			}
		}
	}

	print(dp[N])
}