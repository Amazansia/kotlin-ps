package silver5

fun main() = with(System.`in`.bufferedReader()) {
    val (N, K) = readLine().split(" ").map { it.toInt() }
    var list = mutableListOf<Int>()
    for (i in 0 until N) {
        list.add(i + 1)
    }

    var here = K - 1
    print("<")
    while (list.size != 1) {
        print("${list[here]}, ")
        list.removeAt(here)
        here += K - 1
        if (list.size <= here) here %= list.size
    }
    print("${list[0]}>")
}