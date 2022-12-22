import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
	val N = readLine().toInt()
	var arr = IntArray(N)
	var maxValue = 0
	var location = 0
	var sum = 1
	for (i in 0 until N) {
		arr[i] = readLine().toInt()
		if (maxValue < arr[i]) {
			maxValue = arr[i]
			location = i
		}
	}

	maxValue = arr[N - 1]
	for (i in N - 1 downTo location) {
		if (maxValue < arr[i]) {
			sum++
			maxValue = arr[i]
		}
	}
	print(sum)

}
