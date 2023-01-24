fun main() = with(System.`in`.bufferedReader()) {
    var (N, M) = readLine().split(" ").map { it.toInt() }
    var map = HashMap<String, Boolean>()

    repeat(N) {
        map[readLine()] = false
    }

    var sum = 0
    var answer = mutableListOf<String>()
    repeat(M) {
        var str = readLine()
        if (map.containsKey(str)) {
            sum++
            answer.add(str)
        }
    }

    answer.sort()
    var sb = StringBuilder()
    println(sum)
    answer.forEach {
        sb.append("$it\n")
    }
    print(sb)
}