fun main() = with(System.`in`.bufferedReader()) {
	val (N, M) = readLine().split(" ").map { it.toInt() }.toIntArray()
	var strintmap = HashMap<String, Int>()
	var intstrmap = HashMap<Int, String>()

	for (i in 1..N) {
		var inputstr = readLine()
		strintmap.put(inputstr, i)
		intstrmap.put(i, inputstr)
	}

	for (i in 0 until M) {
		var inputstr = readLine()
		if (inputstr[0].isDigit()) {
			println(intstrmap[inputstr.toInt()])
		} else println(strintmap[inputstr])
	}
}