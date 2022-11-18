fun average_fo_even_num(arr: IntArray): Int {
	var sum = 0
	var cut = 0
	for (i in arr.indices) {
		if (arr[i] % 2 == 0) {
			sum += arr[i]
			cut++
		}
	}
	return sum / cut
}