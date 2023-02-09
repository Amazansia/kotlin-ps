fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()
    var arr = readLine().split(" ").map { it.toInt() }.toIntArray()
    arr.sort()

    var ans = 0

    for (i in arr.indices) {
        var left = 0
        var right = N - 1
        // left < right < N
        while (left < right) {
            if (right == N) break

            if (arr[left] + arr[right] > arr[i]) {
                right--
            } else if (arr[left] + arr[right] < arr[i]) {
                left++
            } else if (left != i && right != i) {
                ans++
                break
            }
            if (left == i) left++
            if (right == i) right--
        }
    }
    print(ans)
}