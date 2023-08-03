package gold4

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()
    var M = readLine().toInt()

    var bus = Array(N) { mutableListOf<Pair<Int, Int>>() }
    for (i in 0 until M) {
        var str = readLine().split(" ").map { it.toInt() }
        bus[str[0]].add(str[1] to str[2])
        bus[str[1]].add(str[0] to str[2])
    }
}