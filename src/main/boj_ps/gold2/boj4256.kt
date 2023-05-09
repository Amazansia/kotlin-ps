package gold2

fun main() = with(System.`in`.bufferedReader()) {
    var T = readLine().toInt()

    var sb = StringBuilder()
    repeat(T) {
        var N = readLine().toInt()
        var pre = IntArray(N + 1) { 0 }
        var In = IntArray(N + 1) { 0 }
        var idx = 0
        readLine().trim().split(" ").map { pre[idx++] = it.toInt() }
        idx = 0
        readLine().trim().split(" ").map { In[idx++] = it.toInt() }

        fun traversal(root: Int, s: Int, e: Int) {
            var rootidx = pre[root]
            for (i in s until e) {
                if (In[i] == rootidx) {
                    traversal(root + 1, s, i)
                    traversal(root + i + 1 - s, i + 1, e)
                    sb.append("$rootidx ")
                }
            }
        }

        traversal(0, 0, N)
        sb.append("\n")
    }
    print(sb)
}