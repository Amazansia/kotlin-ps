package silver1

import kotlin.math.*

/*
??
N 최대 50만
목표는 볼을 이동시켜서 같은 색끼리 모을 수 있는 최소 이동횟수를 찾는 것.
법칙 1. 바로 옆에 다른 색깔의 볼이 있으면 모두 뛰어넘어 옮길 수 있다.
법칙 2. 옮길 수 있는 볼의 색깔은 한가지이다. 빨간색 or 파란색
빨간색 파란색 / 왼쪽 오른쪽 한번씩 옮겨보기
RBBBBBBBBBR일 때, 무조건 시작&끝 색 기준으로 옮기는 게 최적해
* */

fun main() = with(System.`in`.bufferedReader()) {
	var N = readLine().toInt()
	var str = readLine()

	var answer = 500001

	// RB BR 다봐야함
	if (str.first() == str.last()) {
		// BR - move B
		var passed = false
		var count = 0
		for (i in 0 until N) {
			if (passed && str[i] == 'B') {
				count++
			} else if (!passed && str[i] != 'B') {
				passed = true
			}
		}
		answer = min(answer, count)

		if (count == 0) {
			println(0)
			return@with
		}

		// RB - move R
		passed = false
		count = 0
		for (i in 0 until N) {
			if (passed && str[i] == 'R') {
				count++
			} else if (!passed && str[i] != 'R') {
				passed = true
			}
		}
		answer = min(answer, count)

		if (count == 0) {
			println(0)
			return@with
		}

		// BR - move R
		passed = false
		count = 0
		for (i in N - 1 downTo 0) {
			if (passed && str[i] == 'R') {
				count++
			} else if (!passed && str[i] != 'R') {
				passed = true
			}
		}
		answer = min(answer, count)

		if (count == 0) {
			println(0)
			return@with
		}

		// RB - move B
		passed = false
		count = 0
		for (i in N - 1 downTo 0) {
			if (passed && str[i] == 'B') {
				count++
			} else if (!passed && str[i] != 'B') {
				passed = true
			}
		}
		answer = min(answer, count)
	}
	// RB/BR 하나만 보면됨
	else {
		// move to left
		var passed = false
		var count = 0
		for (i in 0 until N) {
			if (passed && str[i] == str.first()) {
				count++
			} else if (!passed && str[i] != str.first()) {
				passed = true
			}
		}
		answer = min(answer, count)

		if (answer == 0) {
			println(0)
			return@with
		}

		// move to right
		passed = false
		count = 0
		for (i in N - 1 downTo 0) {
			if (passed && str[i] == str.last()) {
				count++
			} else if (!passed && str[i] != str.last()) {
				passed = true
			}
		}
		answer = min(answer, count)
	}

	println(answer)
}