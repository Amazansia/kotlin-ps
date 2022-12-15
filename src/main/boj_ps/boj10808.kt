fun main() = with(System.`in`.bufferedReader()) {
	var str = readLine()

	var chararr = IntArray(26)
	for (i in str.indices) {
		chararr[str[i].code - 'a'.code]++
	}

	chararr.forEach {
		print("$it ")
	}
}