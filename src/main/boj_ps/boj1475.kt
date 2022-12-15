fun main() = with(System.`in`.bufferedReader()) {
	val str = readLine()

	var intarr = IntArray(10)

	for (i in str.indices) {
		intarr[str[i].code - '0'.code]++
	}

	intarr[6] = (intarr[6] + intarr[9])
	if (intarr[6] % 2 == 0) intarr[6] /= 2 else intarr[6] = intarr[6] / 2 + 1
	intarr[9] = 0

	print(intarr.maxOrNull())

}