fun main() = with(System.`in`.bufferedReader()) {
    var (N, M) = readLine().split(" ").map { it.toInt() }
    var stuff = ArrayList<Pair<Int, Int>>()
    stuff.add(0 to 0)

    repeat(N) { rr ->
        var temp = readLine().split(" ").map { it.toInt() }.toIntArray()
        var num = temp[2]
        var idx = 1
        // 이진수로 만들기
        /*
        물건이 3개 있다면 1과 2의 조합으로 가능
        물건이 4개 있다면 1 1 2 ->
        물건이 7개 있다면 1 2 4
        물건이 8개 있다면 1 1 2 4 4 2 1 0
        * */

        // 16의 경우?
        while (num > 0) {
            var mul = kotlin.math.min(idx, num)
            stuff.add(temp[0] * mul to temp[1] * mul)
            num -= mul
            idx = idx shl 1
        }
    }
    stuff.add(0 to 0)

    var dp = Array(stuff.size) { IntArray(M + 1) }
    for (i in 1 until stuff.size) {
        for (j in 1..M) {
            // 여기서 i = 4되면 IOB
            if (j < stuff[i].first) {
                dp[i][j] = dp[i - 1][j]
            } else {
                dp[i][j] = kotlin.math.max(dp[i - 1][j], dp[i - 1][j - stuff[i].first] + stuff[i].second)
            }
            //    println("dp[$i][$j] = ${dp[i][j]}")
        }
    }

//    dp.forEach { i ->
//        println(i.map { it })
//    }

//    println("${stuff.size - 1}, $M")
    println(dp[stuff.size - 1][M])

}