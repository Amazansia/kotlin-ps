import java.io.*
import java.util.LinkedList
import java.util.Queue

fun main() = with(System.`in`.bufferedReader()) {

	var bw = BufferedWriter(OutputStreamWriter(System.out))

	var N = readLine().toInt()

	var q: Queue<Int> = LinkedList()

	fun command_Q(line: String) {
		var word = line.split(" ")[0]
		when (word) {
			"push" -> {
				q.add(line.split(" ")[1].toInt())
			}
			"pop" -> {
				if (q.isEmpty()) bw.write("-1\n")
				else {
					bw.write("${q.poll()}\n")
				}
			}
			"size" -> bw.write("${q.size}\n")
			"empty" -> if (q.isEmpty()) bw.write("1\n") else bw.write("0\n")
			"front" -> if (q.isEmpty()) bw.write("-1\n") else bw.write("${q.element()}\n")
			"back" -> if (q.isEmpty()) bw.write("-1\n") else bw.write("${q.last()}\n")
		}
	}

	for (i in 1..N) {
		command_Q(readLine())
	}

	bw.flush()
}


