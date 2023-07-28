package gold2
/*
전선을 최소로 잘라내서 꼬여있는 전선이 하나도 없도록 해봅시당...
오름차순으로...
오름차순 기준으로 잘랐을 때 잘리는 전선 리턴하면 될듯?
흠...
전선이 잘리면 기준 리셋
부분수열의 첫번째가 위로 / 수평으로 / 아래로 연결될 때 세 가지 경우가 있을듯
근데 오름차순 확인만 하면 되니까 상관없지 않을까?
4 3 2 1은?
안될듯
최장 증가 부분수열
10만
?
?

1 4
N^2으로 안됨...NlogN인디
* */

fun main() = with(System.`in`.bufferedReader()) {

    var N = readLine().toInt()
    var arr = readLine().split(" ").map { it.toInt() }.toIntArray()

    // 이탐으로 LIS?
    // 이런거 첨봄...
    var lis = IntArray(N + 1)

    fun lowerBound(num: Int, left: Int, right: Int): Int {
        var mid = 0
        var l = left
        var r = right
        while (l < r) {
            mid = (l + r) / 2
            if (lis[mid] < num) l = mid + 1
            else r = mid
        }
        return r
    }

    var idx = 0

    for (i in 0 until N) {
        if (idx == 0) lis[idx++] = arr[i]
        else {
            if (lis[idx - 1] < arr[i]) lis[idx++] = arr[i]
            else {
                lis[lowerBound(arr[i], 0, idx)] = arr[i]
            }
        }
    }

    println(N - idx)
}