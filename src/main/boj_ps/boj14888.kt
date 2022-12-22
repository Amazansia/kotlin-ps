import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
	val N = readLine().toInt()
	var arr = readLine().split(" ").map { it.toInt() }.toIntArray()
	var arithmetic = readLine().split(" ").map { it.toInt() }.toIntArray()
	var maxvalue = Int.MIN_VALUE
	var minvalue = Int.MAX_VALUE

	// 계산 결과 return
	fun calculation(cal: Int, loca: Int, ari: Int): Int {
		when (ari) {
			0 -> return cal + arr[loca]
			1 -> return cal - arr[loca]
			2 -> return cal * arr[loca]
			3 -> return cal / arr[loca]
		}
		return 0
	}

	fun dfs(arr: IntArray, arithmetic: IntArray, cal: Int, location: Int) {
		if (location == N - 1) {
//			println("dfs cal: $cal")
			maxvalue = max(maxvalue, cal)
			minvalue = min(minvalue, cal)
			return
		}
		// 시간초과 안나려면 다른 조건도 추가해야 할 듯...

		for (i in arithmetic.indices) {
			if (arithmetic[i] != 0) {
				var temp = arithmetic.clone()
				temp[i]--
				dfs(arr, temp, calculation(cal, location + 1, i), location + 1)
			}
		}
	}
//	println()
//	arithmetic.forEach { println(it) }

	dfs(arr, arithmetic, arr[0], 0)
//	arithmetic.forEach { println(it) }

	print("$maxvalue\n$minvalue")
}