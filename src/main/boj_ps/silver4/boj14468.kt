package silver4
/*
어떤 두 소도 길 위의 같은 점을 지나가지 않는다.
소는 26마리고 알파벳으로 이름이 붙는다.
원형 목초지...
중간에
* */

fun main() = with(System.`in`.bufferedReader()) {
    var str = readLine().toCharArray()
    var answer = 0
    var inoutList = Array(26) { intArrayOf(-1, 0) }
    for (i in str.indices) {
        if (inoutList[str[i] - 'A'][0] == -1) inoutList[str[i] - 'A'][0] = i
        else inoutList[str[i] - 'A'][1] = i
    }

    for (i in 0 until 26) {
        for (j in 0 until 26) {
            if (inoutList[i][0] < inoutList[j][0] &&
                inoutList[i][1] < inoutList[j][1] &&
                inoutList[j][0] < inoutList[i][1]
            )
                answer++
        }
    }

    println(answer)
}