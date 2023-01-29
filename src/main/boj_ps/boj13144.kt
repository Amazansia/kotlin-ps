fun main() = with(System.`in`.bufferedReader()) {
	var N = readLine().toInt()
	var arr = readLine().split(" ").map { it.toInt() }.toIntArray()

	var ans: Long = 0
	var left = 0
	var right = 0
	var save = BooleanArray(100001) { false }

	while (right < N) {
		var t = arr[right]
		while (save[t]) {
			ans += right - left
			save[arr[left++]] = false
		}
		save[t] = true
		right++
	}

	ans += (right - left).toLong() * (right - left + 1) / 2
	print(ans)
}