package silver3

fun main() = with(System.`in`.bufferedReader()) {
    var T = readLine().toInt()
    repeat(T) {
        var N = readLine().toInt()
        var map = mutableMapOf<String, Int>()
        for (i in 0 until N) {
            var str = readLine().split(" ")
            if (map.containsKey(str[1])) {
                var temp = map[str[1]]
                if (temp != null) {
                    map[str[1]] = temp + 1
                }
            } else map[str[1]] = 1
        }
        var answer = 1
        map.forEach {
            answer *= (it.value + 1)
        }
        println(answer - 1)
    }
}