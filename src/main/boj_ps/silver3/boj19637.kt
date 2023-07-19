package silver3

import java.lang.StringBuilder
import java.util.*

// 10 000 000 000
// 이렇게 하면 시초

fun main() = with(System.`in`.bufferedReader()) {
	var (N, M) = readLine().split(" ").map { it.toInt() }
	var title: SortedMap<Int, String> = TreeMap()

	// title 입력받기
	for (i in 0 until N) {
		var infos = readLine().split(" ")
		if (title.containsKey(infos[1].toInt())) continue
		title[infos[1].toInt()] = infos[0]
	}

	var sb = StringBuilder()
	// 역순으로 가져오기

	// 10 9 8 7 6 5...key = 8이면
	var keySet = title.keys.reversed()

	fun getTitle(num: Int): String {
		var answer = title[title.lastKey()]

		for (key in keySet) {
			if (key < num) break
			answer = title[key]
		}

		return answer ?: ""
	}

	for (i in 0 until M) {
		sb.append("${getTitle(readLine().toInt())}\n")
	}
	println(sb)
}