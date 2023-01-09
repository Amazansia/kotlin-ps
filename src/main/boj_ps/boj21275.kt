import java.lang.Math.pow
import java.math.BigInteger

fun main() = with(System.`in`.bufferedReader()) {
	/*
	X: 10진법 -> 2^63미만, Long 사용해야 함, 계산시 overflow 조심
	X를 A진법, B진법으로 나타낸 수가 주어진다.
	최대 진수는 0 ~ z까지, 36진수이다.
	최소 2진법부터 시작하여 36진법까지 가능.
	 */

	val (X_A, X_B) = readLine().split(" ")
	var answer = "Impossible"

	fun calculateX(input: String, base: Int): Long {
		var num: Long = 0
		var currentlocation: Long = 0

		for (i in (input.length - 1).downTo(0)) {
			var N = if (input[i].isDigit()) input[i].code else input[i].code - 'a'.code + 10
			var big: BigInteger = (N * currentlocation + num).toBigInteger()
			if (big < Long.MAX_VALUE.toBigInteger()) {
				return -1
			}
			num += N * currentlocation

			if (currentlocation == 0.toLong()) {
				currentlocation = 1
				num += N
			}
			currentlocation *= base
		}
		return num
	}

	// i진법으로 표기된 X_A의 값을 10진법으로 변환하여 temp에 저장
	for (i in 2..36) {
		if (i <= maxBase(X_A)) continue
		val temp: Long = calculateX(X_A, i)
		if (temp < 0) break
		for (j in 2..36) {
			if (i == j || j <= maxBase(X_B)) continue
			if (calculateX(X_B, j) < 0) break
			if (temp == calculateX(X_B, j) && answer == "Impossible") {
				answer = "$temp $i $j"
			} else if (temp == calculateX(X_B, j) && answer != "Impossible") {
//				println(temp)
//				println(answer)
				print("Multiple")
				return
			}
		}
	}

	print(answer)

}

fun maxBase(str: String): Int {
	var num = 2
	for (i in str.indices) {
		var N = if (str[i].isDigit()) str[i].code else str[i].code - 'a'.code + 10
		if (num < N) {
			num = N
		}
	}
	return num
}
