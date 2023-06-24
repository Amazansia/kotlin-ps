package gold3

fun main() = with(System.`in`.bufferedReader()) {
    var (_, K) = readLine().split(" ").map { it.toInt() }
    var str = readLine()
    var arr = str.toCharArray()
    var dq = ArrayDeque<Char>()
    for (i in arr.indices) {
        while (K > 0 && dq.isNotEmpty() && dq.last() < arr[i]) {
            dq.removeLast()
            K--
        }
        dq.addLast(arr[i])
    }

    var sb = StringBuilder()
    while (dq.size > K) {
        sb.append(dq.removeFirst())
    }

    print(sb)
}