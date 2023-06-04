fun main() = with(System.`in`.bufferedReader()) {
	var sb = StringBuilder()
	repeat(100000) {
		sb.append("1000")
		sb.append("\n")
	}
	sb.deleteAt(sb.length - 1)
	print(sb)
}