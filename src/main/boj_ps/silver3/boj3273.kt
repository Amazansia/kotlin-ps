package silver3

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    var intarr: IntArray = readLine().split(" ").map { it.toInt() }.toIntArray()
    val x = readLine().toInt()

    var savedarr = IntArray(1000001)

    var sum: Long = 0

    for (i in intarr) {
        savedarr[i]++
    }

    intarr.sort()

    for (i in intarr) {
        if (((x - i) > 0) && ((x - i) < 1000001) && (savedarr[x - i] != 0)) {
            sum += savedarr[i] * savedarr[x - i]
        }
    }

    print(sum / 2)
}