fun main() = with(System.`in`.bufferedReader()) {
	var M = readLine().toInt()

	// num!의 0의 개수를 리턴
	fun numOf5(num: Int): Int {
		var res = 0
		var n = num
		while (n > 0) {
			res += n / 5
			n /= 5
		}
		return res
	}

	// low와 high 모두 5의 배수로 계산
	var low = 0
	var high = M * 5
	var mid = 0

	// mid -> 0의 개수
	while (low <= high) {
		mid = (low + high) / 2
		if (numOf5(mid) >= M) {
			high = mid - 1
		} else {
			low = mid + 1
		}
	}

	println(if(numOf5(low) == M) low else -1)
}