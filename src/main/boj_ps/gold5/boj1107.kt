package gold5

import kotlin.math.*

/*
고장나지 않은 버튼으로 리모콘을 눌러서 원하는 채널로 이동하는 최소 횟수
각 숫자 자리수마다 고장나지 않은 가장 가까운 버튼을 눌러본다?
511111
500000
11111+6 -> 11117
5457
5459
-2
-> 6
이렇게 계산한 값 & 시작값인 100과 비교해서 작은 값 출력하면 될듯
윗채널에서 내려오는 값 & 아래채널에서 올라오는 값 둘다 비교해야될거같은데

dfs로 3가지 경우 고려
각 자리마다
1. n보다 큰 근사값
2. n
3. n보다 작은 근사값
각 값이 존재하는 경우에만 고려하며 다음 계산에 넣는다
최종적으로 sb.length == N.toString().length일 경우에 stop

위처럼 하면 자릿수가 바뀔 때를 계산할 수 없음
예를 들어
10000
2
0 1
이때 9999를 완성하고 +1을 해주면 5번만에 완성시킬 수 있지만 위의 예시로는 각 자릿수를 고려하기 때문에
무조건 5자리수에서만 경우의 수를 고려하게 된다
그냥 모든 경우의 수를 dfs로 고려해주면 될듯
*/

fun main() = with(System.`in`.bufferedReader()) {
	var N = readLine().trim().toInt()
	var M = readLine().trim().toInt()
	var buttons = BooleanArray(10) { true }
	if (M != 0) {
		readLine().trim().split(" ").map { it.toInt() }
			.forEach { buttons[it] = false }
	}

	var res = 987654321

	// 현재 완성된 int값, 현재 자릿수(큰 자릿수부터 12345...)
	// dfs가 끝나면 주어진 buttons로 만들 수 있는 N에 가장 근사한 수가 res에 저장된다.
	// 0
	// 2
	// 0 1
	// 위의 경우 답은 3
	// 2를 누르고 -1, -1을 해야 함
	// N == 0일 경우 res값이 0으로 고정되어버린다
	fun dfs(now: Int, cnt: Int, end: Int) {

		if(cnt != 0)
			res = Math.min(res, abs(N - now))

		if (cnt == end) {
			return
		}

		for (i in 0..9) {
			if (buttons[i])
				dfs(now * 10 + i, cnt + 1, end)
		}
	}

	var answer = 987654321

	for (i in 1..6) {
		dfs(0, 0, i)
		answer = Math.min(answer, res + i)
//		println("answer = $answer, res = $res")
	}

	println(Math.min(answer, abs(N - 100)))
}