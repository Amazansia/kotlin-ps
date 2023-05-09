package gold4

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    var T = readLine().toInt()
    repeat(T) {
        var K = readLine().toInt()
        var smap: SortedMap<Int, Int> = TreeMap()

        for (i in 0 until K) {
            var str = readLine().split(" ")
            // I
            if (str[0] == "I") {
                smap[str[1].toInt()] = smap.getOrDefault(str[1].toInt(), 0) + 1
            }
            // D
            else if (smap.isNotEmpty()) {
                var num = if (str[1] == "1") smap.lastKey() else smap.firstKey()
                if (smap[num] == 1) smap.remove(num)
                else smap[num] = smap[num]!! - 1
            }
        }

        println(if (smap.isEmpty()) "EMPTY" else "${smap.lastKey()} ${smap.firstKey()}")
    }
}