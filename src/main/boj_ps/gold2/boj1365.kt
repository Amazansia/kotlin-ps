package gold2

fun main() = with(System.`in`.bufferedReader()) {

	var N = readLine().toInt()
	var arr = readLine().split(" ").map { it.toInt() }.toIntArray()

	var lis = IntArray(N + 1)

	fun lowerBound(num: Int, left: Int, right: Int): Int {
		var l = left
		var r = right

		while (l < r) {
			var mid = (l + r) / 2
			if (lis[mid] < num) l = mid + 1
			else r = mid
		}
		return r
	}

	var idx = 0

	for (i in 0 until N) {
		if (idx == 0) lis[idx++] = arr[i]
		else {
			if (lis[idx - 1] < arr[i]) lis[idx++] = arr[i]
			else {
				lis[lowerBound(arr[i], 0, idx)] = arr[i]
			}
		}
	}

	println(N - idx)
}