fun main() = with(System.`in`.bufferedReader()) {
	var sb = StringBuilder()
	fun checkTriangle(list: List<Int>): String {
		if (list[0] >= list[1] + list[2]) return "Invalid"
		if (list[0] == list[1] && list[1] == list[2]) return "Equilateral"
		if(list[0] != list[1] && list[1] != list[2] && list[0] != list[2]) return "Scalene"
		else return "Isosceles"
	}

	while (true) {
		var str = readLine()
		if (str == "0 0 0") break
		var list = str.split(" ").map { it.toInt() }.sortedDescending()
		sb.appendLine(checkTriangle(list))
	}
	print(sb)
}