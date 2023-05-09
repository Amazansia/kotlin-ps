package bronzes

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()

    var list = IntArray(10001)
    repeat(N) {
        list[readLine().toInt()]++
    }

    val bw = System.`out`.bufferedWriter()
    for (i in list.indices) {
        bw.write("$i\n".repeat(list[i]))
    }

    bw.close()
}