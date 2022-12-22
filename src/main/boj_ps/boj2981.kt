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

//	// 나머지가 같다는 것...
//	// bf: 이중 for문으로 돌려보면... -> 시간 초과
//	for (i in 2..numlist.maxOrNull()!!) {
//		var flag = true
//		var temp: Int = numlist[0] % i
//		for (num in numlist) {
//			if (temp != num % i) {
//				flag = !flag
//				break
//			}
//		}
//		if (flag) {
//			strbd.append(i.toString() + " ")
//		}
//	}

	// bf로는 안되는듯? 알고리즘 문제도 아니고
	// 수학계산 같은 걸 해야 될 것 같은데
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