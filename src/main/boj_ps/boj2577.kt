fun main() = with(System.`in`.bufferedReader()) {
	var A = readLine().toInt()
	var B = readLine().toInt()
	var C = readLine().toInt()

	var intarr = IntArray(10)

	var sum = (A * B * C).toString()
	for (i in sum.indices) {
		intarr[sum[i].code - '0'.code]++
	}

	intarr.forEach {
		println(it)
	}
}