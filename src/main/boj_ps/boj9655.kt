fun main() = with(System.`in`.bufferedReader()) {
	var N = readLine().toInt()
	println(if (N % 2 == 0) "CY" else "SK")
}