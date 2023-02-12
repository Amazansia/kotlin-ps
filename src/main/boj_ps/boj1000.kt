fun main() = with(System.`in`.bufferedReader()) {
    var (A, B) = readLine().split(" ").map { it.toInt() }
    print(A + B)
}

