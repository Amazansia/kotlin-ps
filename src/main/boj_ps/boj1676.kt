import java.lang.Integer.min

fun main() = with(System.`in`.bufferedReader()) {
    // 2와 5의 개수 min값 구하면 되는거 아닐가요?
    val N = readLine().toInt()
    var sum2 = 0
    var sum5 = 0
    for (i in 1..N) {
        var n2 = i
        while (n2 % 2 == 0) {
            n2 /= 2
            sum2++
        }
        var n5 = i
        while (n5 % 5 == 0) {
            n5 /= 5
            sum5++
        }
    }
    print(min(sum2, sum5))
}