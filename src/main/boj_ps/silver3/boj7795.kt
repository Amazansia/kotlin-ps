package silver3
/*
자기보다 크기가 작은 먹이만 먹을 수 있다
쌍의 개수?
정렬하고 arrA[i]보다 작은 먹이가 arrB의 몇번째 원소인지 카운트해서 더하기
이거 "쌍의 개수"를 물어보는 문제라 distinct로 중복 제거해줘야 할 것 같은데 그렇게 하니까 틀리고
중복제거 안하니까 맞는다;;;왜지?
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

//        println()
//        println(arrA.joinToString())
//        println(arrB.joinToString())
        /*

        1 3 7 8
        1 3 6

        1 - 0
        3 - 1
        7 - 3
        8 - 3
        * */

        /* arrB[mid]가 처음으로 arrA[idx]보다 크거나 같아지는 mid값을 리턴하는 함수 */
        fun getPreyCount(idx: Int): Int {
            var l = 0
            var r = M - 1
            var res = 0
            while (l <= r && l in arrB.indices && r in arrB.indices) {
                var mid = (l + r) / 2
//                println(mid)
                if (arrB[mid] < arrA[idx]) {
                    l = mid + 1
                    res = l
                } else {
                    r = mid - 1
                }
            }
//            println("l:$l, r:$r, mid:$mid")
            return res
        }

        var answer = 0
        for (i in arrA.indices) {
            answer += getPreyCount(i)
        }

        sb.append(answer.toString() + "\n")
    }
    print(sb)
}