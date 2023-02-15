fun main() = with(System.`in`.bufferedReader()) {
	var (N, Q) = readLine().split(" ").map { it.toInt() }
	var landArray = IntArray(N)
	repeat(Q) {
		landArray[it] = readLine().toInt()
	}
}