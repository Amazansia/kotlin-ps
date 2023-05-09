package bronzes

fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    var arr = readLine()
    var sum = 0
    for (i in arr.indices) {
        sum += arr[i].code - '0'.code
    }
    print(sum)
}