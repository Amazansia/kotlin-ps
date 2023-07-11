fun main() = with(System.`in`.bufferedReader()) {
	var N = readLine().toInt()
	var infos = Array(N) { IntArray(3) }

	for (i in 0 until N) {
		infos[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
	}

	var answer = IntArray(1000)

	// 몇개의 숫자가 일치하는지, 몇 자리가 일치하는지 알 수 있다면 모든 경우를 고려할 수 있다
	fun check(num: Int, strike: Int, ball: Int) {
		var n = num.toString().toCharArray()

		for (i in 100..999) {
			var sCnt = 0
			var bCnt = 0
			var ii = i.toString().toCharArray()
			for (k in ii.indices) {
				if (n[k] == ii[k]) sCnt++
				if (n.contains(ii[k])) bCnt++
			}
			// 자리가 일치하지 않으면서 볼인 경우
			var bc = bCnt - sCnt
			if (sCnt == strike && bc == ball
				&& ii[0] != ii[1]  && ii[0] != ii[2] && ii[1] != ii[2]
				&& !ii.contains('0'))
				answer[i]++
		}

	}

	for (info in infos) {
		check(info[0], info[1], info[2])
	}

	println(answer.filterIndexed { index, i -> index in 100..999 && i == N }.count())
}