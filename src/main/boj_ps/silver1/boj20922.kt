package silver1

import kotlin.math.*

/*
겹치는게 싫어
같은 원소가 K개 이하로 들어 있는 최장 연속 부분 수열의 길이 구하기
길이가 N인 수열
N은 20만, K는 100
투포인터
*/


fun main() = with(System.`in`.bufferedReader()) {
	var (N, K) = readLine().split(" ").map { it.toInt() }
	var arr = readLine().split(" ").map { it.toInt() }.toIntArray()

	var info = IntArray(100001)

	var answer = 0
	var l = 0
	var r = 0

	while (l <= r && r < N) {
		// 현재 arr[r]의 cnt가 K 이하일 때 진행가능
		if (info[arr[r]] < K) {
			info[arr[r]]++
			r++
		}
		// 진행 불가능
		else {
			info[arr[l]]--
			l++
		}
		answer = max(answer, r - l)
	}

	println(answer)
}