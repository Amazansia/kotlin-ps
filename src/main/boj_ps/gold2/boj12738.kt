package gold2
/*
LIS
인데 범위가 어떻게백만
NlogN...이면
600만
될듯
이분탐색 가 보 자 고
* */

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()
    var arr = readLine().split(" ").map { it.toInt() }.toIntArray()

    var LIS = IntArray(N)

    // LIS의 시작 인덱스와 끝 인덱스, num을 받아 LIS 내부에서 num이 위치해야 하는 idx값을 리턴하는 함수
    fun binarySearch(start: Int, end: Int, num: Int): Int {
        var s = start
        var e = end

        while (s < e) {
            var mid = (s + e) / 2
            if (LIS[mid] < num) {
                s = mid + 1
            } else
                e = mid
        }

        return e
    }

    var idx = 0
    LIS[0] = arr[0]

    for (i in arr.indices) {
        if (LIS[idx] < arr[i])
            LIS[++idx] = arr[i]
        else
            LIS[binarySearch(0, idx, arr[i])] = arr[i]
    }

    println(idx + 1)
}