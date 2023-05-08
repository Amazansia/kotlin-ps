package bronzes

/*
1 0
2~7 1
8~19 2
20~37 3...
* */
fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().trim().toInt() - 1
    var m = 0
    while (N > 0) {
        m++
        N -= 6 * m
    }
    println(m + 1)
}