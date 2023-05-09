package silver4

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    var arr = readLine().split(" ").map { it.toInt() }.toIntArray()
    arr.sort()
    var sum = 0
    var current = 0
    for (i in arr.indices) {
        current += arr[i]
        sum += current
    }
    print(sum)
}