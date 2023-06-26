package gold5

import java.lang.StringBuilder
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
*/

fun main() = with(System.`in`.bufferedReader()) {
	var N = readLine().toInt()
	var M = readLine().toInt()
	var buttons = BooleanArray(10)
	if (M != 0) {
		readLine().split(" ").map { it.toInt() }
			.forEach { buttons[it] = false }
	}

	println(buttons.toString())

	fun getUpperNum(n: Int): Int {
		if (buttons[n]) return n

		var res = -1
		for (i in n..9) {
			if (buttons[i] && abs(i - n) < abs(res - n)) res = i
		}
		return res
	}

	fun getDownerNum(n: Int): Int {
		if (buttons[n]) return n

		var res = -1
		for (i in 0..9) {
			if (buttons[i] && abs(i - n) < abs(res - n)) res = i
		}
		return res
	}

	var sb1 = StringBuilder()
	N.toString().toCharArray().forEach {
		sb1.append(getUpperNum(it - '0'))
	}

	var sb2 = StringBuilder()
	N.toString().toCharArray().forEach {
		sb2.append(getDownerNum(it - '0'))
	}
	var answer = Math.min(sb1.toString().toInt(), sb2.toString().toInt())
//	println(Math.min(sb.toString().toInt() - N, N - 100))
}