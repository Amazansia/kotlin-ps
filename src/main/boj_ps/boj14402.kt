fun main() = with(System.`in`.bufferedReader()) {
	var q = readLine().toInt()

	// 출입기록 관리 +, -
	var hMap = HashMap<String, Int?>()
	// 정상 출입수
	var sum = 0
	// 정보 저장
	for (i in 0 until q) {
		var str = readLine().split(" ")
		// 정상적인 출입일 경우 전체에서 뺌 A+, A-
		if (str[1] == "+") {
			hMap[str[0]] = hMap.getOrDefault(str[0], 0)?.plus(1)
		} else if (hMap.containsKey(str[0]) && hMap[str[0]] != 0) {
			hMap[str[0]] = hMap[str[0]]?.minus(1)
			sum += 2
//			println("$i, ${hMap[str[0]]}")
		}
	}

	print(q - sum)
}