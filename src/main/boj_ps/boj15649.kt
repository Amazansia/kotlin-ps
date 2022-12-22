import java.io.BufferedWriter
import java.io.OutputStreamWriter

fun main() = with(System.`in`.bufferedReader()) {

	var bw = BufferedWriter(OutputStreamWriter(System.out))

	var temp = readLine().split(" ")
	val N = temp[0].toInt()
	val M = temp[1].toInt()

	var visited = Array(N + 1) { false }

	fun printStr(str: String) {
		for (i in str.indices) {
			bw.write("${str[i]} ")
		}
		bw.write("\n")
	}

	fun backtracking(str: String) {
		if (str.length >= M) {
			printStr(str)
			return
		}
		for (i in 1..N) {
			if (visited[i]) continue
			visited[i] = true
			backtracking("$str$i")
			visited[i] = false
		}
	}

	backtracking("")

	bw.flush()
}