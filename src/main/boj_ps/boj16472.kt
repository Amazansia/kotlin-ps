fun main() = with(System.`in`.bufferedReader()) {
	var N = readLine().toInt()
	var str = readLine()

	var alphabet = IntArray(26) { 0 }

	var answer = 0

	var s = 0
	var e = 0

	alphabet[str[0].code - 'a'.code]++
	var cnt = 1
	while (true) {
		if (e >= str.length || s >= str.length) break
		if (cnt <= N) {
			e++
			if (e == str.length) break
			if (alphabet[str[e].code - 'a'.code] == 0) {
				cnt++
			}
			alphabet[str[e].code - 'a'.code]++

			if (cnt <= N) {
				answer = kotlin.math.max(answer, e - s + 1)
			}
		} else {
			alphabet[str[s].code - 'a'.code]--
			if (alphabet[str[s].code - 'a'.code] <= 0) {
				cnt--
				alphabet[str[s].code - 'a'.code] = 0
			}
			s++
		}
	}

	print(answer)
}