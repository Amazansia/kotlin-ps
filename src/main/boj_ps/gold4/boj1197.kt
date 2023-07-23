package gold4

fun main() = with(System.`in`.bufferedReader()) {
    var (V, E) = readLine().split(" ").map { it.toInt() }
    var arr = Array(E) { Triple(0, 0, 0) }
    for (i in 0 until E) {
        arr[i] = readLine().split(" ").map { it.toInt() }.let { Triple(it[0], it[1], it[2]) }
    }
}