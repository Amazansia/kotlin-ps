package gold4

fun main() = with(System.`in`.bufferedReader()) {
    var (N, S) = readLine().split(" ").map { it.toInt() }
    var arr = readLine().split(" ").map { it.toInt() }.toIntArray()

    var sum = 0
    var len = 1000001

    var left = 0
    var right = 0

    while (true) {
        if (sum >= S) {
            sum -= arr[left]
            left++
            len = kotlin.math.min(len, right - left + 1)
        } else if (right == arr.size) {
            break
        } else {
            sum += arr[right]
            right++
        }
    }

    print(if (len == 1000001) 0 else len)
}