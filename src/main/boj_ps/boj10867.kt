import java.util.SortedSet

fun main() = with(System.`in`.bufferedReader()) {
	readLine().toInt()
	val saveset: SortedSet<Int> = readLine().split(" ").map { it.toInt() }.toSortedSet()

	saveset.forEach { print("$it ") }
}