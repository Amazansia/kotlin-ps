package gold4

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()

    var numlist = IntArray(N)

    for (i in 0 until N) {
        numlist[i] = readLine().toInt()
    }

    numlist.sort()

    var gcdval = numlist[1] - numlist[0]
    var strbd = StringBuilder("")


    for (i in 2 until N) {
        gcdval = gcd(gcdval, numlist[i] - numlist[i - 1])
    }

    for (i in 2..gcdval) {
        if (gcdval % i == 0) strbd.append("$i ")
    }

    println(strbd)
}

fun gcd(a: Int, b: Int): Int {
    var tempA = a
    var tempB = b
    while (tempB != 0) {
        var temp = tempA % tempB
        tempA = tempB
        tempB = temp
    }
    return tempA
}