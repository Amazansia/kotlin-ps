fun main() = with(System.`in`.bufferedReader()) {
	val N = readLine().toInt()
	val numSet = HashSet<Int>()

	var numlist = readLine().split(" ")
	for (num in numlist) {
		numSet.add(num.toInt())
	}

	val M = readLine().toInt()
	numlist = readLine().split(" ")
	for (num in numlist) {
		if (numSet.contains(num.toInt())) {
			println("1")
		} else {
			println("0")
		}
	}
}