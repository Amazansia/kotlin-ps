fun main() = with(System.`in`.bufferedReader()) {
	val N = readLine().toInt()
	var numlist = readLine().split(" ").map { i -> i.toInt() }
	val v = readLine().toInt()
	print(numlist.count { it == v })
}