fun main() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(" ").map { it.toInt() }
    var arr_A = Array(N) { BooleanArray(M) { false } }
    var arr_B = Array(N) { BooleanArray(M) { false } }

    repeat(N) {
        var str = readLine()
        for (i in 0 until M) {
            // '1'일 때 true로 저장됨
            arr_A[it][i] = str[i] != '0'
        }

    }
    repeat(N) {
        var str = readLine()
        for (i in 0 until M) {
            arr_B[it][i] = str[i] != '0'
        }
    }

    fun reverseA(x: Int, y: Int) {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                var temp = arr_A[x + i][y + j]
                arr_A[x + i][y + j] = !temp
            }
        }
    }

    fun checkAB(): Boolean {
        for (i in 0 until N) {
            for (j in 0 until M) {
                if (arr_A[i][j] != arr_B[i][j]) {
                    return false
                }
            }
        }
        return true
    }

    fun check3(x: Int, y: Int): Boolean {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (arr_A[x + i][y + j] != arr_B[x + i][y + j]) return true
            }
        }
        return false
    }

    fun printA() {
        println("printA")
        for (i in 0 until N) {
            for (j in 0 until M) {
                print(if (arr_A[i][j]) '1' else '0')
            }
            println()
        }
    }

    var sum = 0
    for (i in 0 until N) {
        for (j in 0 until M) {
            if (!(i + 3 > N || j + 3 > M) && check3(i, j)) {
                reverseA(i, j)
                sum++
            }
            printA()
            if (checkAB()) {
                print(if (sum != 0) sum else -1)
                return
            }
        }
    }
    print(-1)
}