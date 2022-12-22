import java.io.BufferedWriter
import java.io.OutputStreamWriter

fun main() = with(System.`in`.bufferedReader()) {

	var bw = BufferedWriter(OutputStreamWriter(System.out))

	val T = readLine().toInt()

	repeat(T) {
		var N = readLine().toInt()
		var hMap = HashMap<Int, Boolean>()
		var list1 = readLine().split(" ").map { it.toInt() }.toIntArray()
		for (i in list1) {
			hMap[i] = true
		}

		var M = readLine().toInt()
		var list2 = readLine().split(" ").map { it.toInt() }.toIntArray()
		for (i in list2) {
			if (hMap[i] == true) bw.append("1\n")
			else bw.append("0\n")
		}
		bw.flush()
	}
	bw.close()
}