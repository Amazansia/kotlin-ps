package silver5

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    readLine().toInt()
    val saveset: SortedSet<Int> = readLine().split(" ").map { it.toInt() }.toSortedSet()

    saveset.forEach { print("$it ") }
}