import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
	var N = readLine().toInt()
	var solution = readLine().split(" ").map { it.toInt() }.toIntArray()
	solution.sort()
	var left = 0
	var right = solution.size - 1
	var ansLeft = left
	var ansRight = right

	//
	var ans = Int.MAX_VALUE
	while (left <= right) {
		var sum = solution[left] + solution[right]
		if(abs(ans) < abs(sum)){
			ansLeft = left
			ansRight = right
		}
		if (ans > 0) {
			right--
		} else if (ans < 0) {
			left++
		} else {
			break
		}
	}

	print("${solution[ansLeft]} ${solution[ansRight]}")
}