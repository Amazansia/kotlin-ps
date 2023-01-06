fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine()
    var arr = readLine().split(" ").map { it.toInt() }.toIntArray()

    // true면 소수, false면 소수가 아니다
    var booleanarr = BooleanArray(1001) { true }
    booleanarr[0] = false
    booleanarr[1] = false

    for (i in 2..1000) {
        for (j in 2..1000) {
            if (i * j <= 1000 && booleanarr[i * j]) {
                booleanarr[i * j] = false
            }
        }
    }

    var sum = 0
    arr.forEach {
        if (booleanarr[it]) sum++
    }
    print(sum)
}