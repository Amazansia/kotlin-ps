/*
boolArray[10]으로 저장
이동 경로 체크
1 5 9 8 7 3 -> 가능
3 4 가능? -> 가능

123
456
789
인접하지 않는 점은 8개밖에 없다...
* */
fun main() = with(System.`in`.bufferedReader()) {
    var L = readLine().trim().toInt()
    var pattern = readLine().trim().split(" ").map { it.toInt() }.toIntArray()
    var answer = "YES"
    var visited = BooleanArray(10)

    visited[pattern.first()] = true
    for (i in 0 until L - 1) {
        var a = pattern[i]
        var b = pattern[i + 1]

        if (visited[b]) {
            answer = "NO"
            break
        }
        visited[b] = true

        if (a > b) {
            a = pattern[i + 1]
            b = pattern[i]
        }
        if ((((a == 1 && b == 9) || (a == 2 && b == 8) || (a == 3 && b == 7) || (a == 4 && b == 6)) && !visited[5])
            || (a == 1 && b == 3 && !visited[2])
            || (a == 1 && b == 7 && !visited[4])
            || (a == 3 && b == 9 && !visited[6])
            || (a == 7 && b == 9 && !visited[8])
        ) {
            answer = "NO"
            break
        }
    }
    print(answer)
}