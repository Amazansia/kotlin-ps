import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
	var array = arrayOf(1, 6, 4, 5, 2, 3, 4, 5)
	var length = IntArray(8)
	for (i in array.indices) {
		length[i] = 1
		for (j in 0 until i) {
			if (array[j] < array[i]) length[i] = max(length[i], length[i] + 1)
		}
	}

	var LIS = IntArray(8)

	fun binarySearch(start: Int, end: Int, num: Int): Int {
		var s = start
		var e = end

		while (s < e) {
			var mid = (s + e) / 2
			if (LIS[mid] < num) {
				s = mid + 1
			} else
				e = mid
		}

		return e
	}

	var idx = 0
	LIS[0] = array[0]

	var record = IntArray(8)

	for (i in array.indices) {
		if (LIS[idx] < array[i]) {
			LIS[++idx] = array[i]
			record[idx] = i
		} else {
			var bsIdx = binarySearch(0, idx, array[i])
			LIS[bsIdx] = array[i]
			record[bsIdx] = i
		}
	}

	for (i in 0 .. idx) {
		println(array[record[i]])
	}
}