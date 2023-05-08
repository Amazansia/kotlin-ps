package silver3

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()
    var arr = readLine().split(" ").map { it.toInt() }.toIntArray()
    var budget = readLine().toInt()

    // 배정된 예산 중 최댓값 출력...
    arr.sort()

    // NlogN -> 상한액을 이분탐색으로 계산
    var low = 0
    var high = arr.last()

    fun sumWithFloor(floor: Int): Int {
        var res = 0
        for (i in arr.indices) {
            res += if (arr[i] > floor) floor else arr[i]
        }
        return res
    }

    var mid = 0

    while (low <= high) {
        mid = (low + high) / 2
        when {
            sumWithFloor(mid) > budget -> high = mid - 1
            else -> low = mid + 1
        }
    }
    print(high)
}