fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val houses = readLine().split(" ").map { it.toInt() }.toIntArray()
    houses.sort()
    println(houses[(houses.size - 1) / 2])
}