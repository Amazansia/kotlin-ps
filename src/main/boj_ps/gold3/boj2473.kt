package gold3

/*
한번정렬하고
0~N-1까지 순회(N):
차례대로 투포인터 탐색(N)하면서 최적값에 가까운지 체크하기

* */
fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()
    var arr = readLine().split(" ").map { it.toInt() }.toIntArray()

    var answer = Triple(arr[0], arr[1], arr[2])

    for (i in arr) {
        var sum = i
        var l = 0
        var r = arr.size - 1
        while (l < r) {

        }
    }
}