import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
	val N = readLine().toInt()
	val houses = readLine().split(" ").map { it.toInt() }.toIntArray()
	var sumL: Long = 0
	houses.forEach {
		sumL += it
	}
}