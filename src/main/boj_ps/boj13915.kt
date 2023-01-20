fun main() = with(System.`in`.bufferedReader()) {
	// 숙련도 분류: 비행해본 열기구 종류 수 -> 최대 9
	// 구하는 것: 총 몇 개의 숙련도로 분류되는지 1 ~ 9

	fun protoString(str: String): String {
		var temp = str.toSortedSet()
		var res = StringBuilder("")
		temp.forEach {
			res.append(it)
		}
		return res.toString()
	}
/*
	try {
		var str = readLine()
		var save = HashSet<String>()
		for (i in 0 until str.toInt()) {
			var student = readLine()
			save.add(protoString(student))
		}
		println(save.size)
	} catch (e: NullPointerException) {

	}
*/
	do {
		var str = readLine()
		var save = HashSet<String>()
		if (str == null || str.equals("")) continue
		for (i in 0 until str.toInt()) {
			var student = readLine()
			save.add(protoString(student))
		}
		println(save.size)

	} while (str != null && !str.equals(""))
}