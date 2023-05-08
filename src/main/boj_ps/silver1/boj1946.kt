package silver1

fun main() = with(System.`in`.bufferedReader()) {
    val T = readLine().toInt()
    repeat(T) {
        val N = readLine().toInt()
        var arr = IntArray(N + 1)
        for (i in 1..N) {
            var str = readLine().split(" ")
            arr[str[0].toInt()] = str[1].toInt()
        }
        var survival = arr[1]
        var sum = 1
        for (i in 2..N) {
            if (survival > arr[i]) {
                survival = arr[i]
                sum++
            }
        }
        println(sum)
    }
}