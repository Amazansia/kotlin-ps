package silver2

// list에 수를 계속 추가하면서 만약 list.last() > arr[i]면 list[list.size-1] = arr[i]

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()
    var arr = readLine().split(" ").map { it.toInt() }.toIntArray()

    var list = mutableListOf<Int>()
    list.add(arr[0])

    // 1 6 4 5 2 3 5 7 9 8
    /*
    1 6
    1 4
    1 4 5

    * */

    // 자리 찾아주는 이분탐색 함수
    // s~e 사이에서 num이 위치해야 하는 idx값을 리턴한다
    fun bs(s: Int, e: Int, num: Int): Int {
        var start = s
        var end = e
        while (start < end) {
            var mid = (start + end) / 2
            if (num > list[mid]) start = mid + 1
            else end = mid
        }
        return end
    }

    for (i in 1 until N) {
        // list.last보다 arr[i]가 크다면 arr[i]로 덧붙여준다
        if (list.last() < arr[i]) {
            list.add(arr[i])
        }
        // last보다 arr[i]가 작다면 arr[i]의 위치를 찾아줘야 함
        var idx = bs(0, list.size - 1, arr[i])
        list[idx] = arr[i]
    }

    println(list.size)
}