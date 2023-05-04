package silver4

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    var arrA = readLine().split(" ").map { it.toInt() }.toIntArray()
    var arrB = readLine().split(" ").map { it.toInt() }.toIntArray()
    arrA.sort()
    arrB.sortDescending()
    var sum = 0
    for (i in 0 until N) {
        sum += arrA[i] * arrB[i]
    }
    println(sum)
}