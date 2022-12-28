fun main() = with(System.`in`.bufferedReader()) {
	val T = readLine().toInt()
	for (i in 0 until T) {
		var sb = StringBuilder()
		var C = readLine().toInt()
		sb.append("${C / 25} ")
		C %= 25
		sb.append("${C / 10} ")
		C %= 10
		sb.append(("${C / 5} "))
		C %= 5
		sb.append("$C")
		println(sb)
	}
}