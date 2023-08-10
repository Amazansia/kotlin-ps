package silver3
/*
자기보다 크기가 작은 먹이만 먹을 수 있다
쌍의 개수?
정렬하고 arrA[i]보다 작은 먹이가 arrB의 몇번째 원소인지 카운트해서 더하기
* */

fun main() = with(System.`in`.bufferedReader()) {
    var T = readLine().toInt()
    var sb = StringBuilder()
    repeat(T) {
        var (N, M) = readLine().split(" ").map { it.toInt() }
        var arrA = readLine().split(" ").map { it.toInt() }.toIntArray()
        var arrB = readLine().split(" ").map { it.toInt() }.toIntArray()
        arrA.sort()
        arrB.sort()

        /* arrB[mid]가 처음으로 arrA[idx]보다 크거나 같아지는 mid값을 리턴하는 함수 */
        fun getPreyCount(idx: Int): Int {
            var l = 0
            var r = M - 1
            var mid = 0
            while (l <= r) {
                mid = (l + r) / 2
                if (arrB[mid] <= arrA[idx]) {
                    l = mid + 1
                } else {
                    r = mid - 1
                }
            }
            return mid
        }

        var answer = 0
        for (i in 0 until N) {
            answer += getPreyCount(i)
            println("$answer, ${getPreyCount(i)}")
        }

        sb.append(answer.toString() + "\n")
    }
    print(sb)
}