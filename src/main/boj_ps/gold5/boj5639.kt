package gold5
/*
재귀로 푸는게 나을듯
* */

fun main() = with(System.`in`.bufferedReader()) {
    var tree = IntArray(10001)
    var idx = 0
    var now = readLine()
    while (!now.isNullOrEmpty()) {
        tree[idx++] = now.toInt()
        now = readLine()
        if (now.isNullOrEmpty()) break
    }

    fun recursive(n: Int, end: Int) {
        if (n > end)
            return
        var mid = n + 1
        while (mid <= end && tree[mid] < tree[n]) mid++
        recursive(n + 1, mid - 1)
        recursive(mid, end)
        println(tree[n])
    }

    recursive(0, idx - 1)
}