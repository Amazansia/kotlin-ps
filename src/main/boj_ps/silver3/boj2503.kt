package silver3

/*
dfs?
3자리가 전부니까 100~999까지 해도 최대 900번 돈다
완탐도 가능...

* */
fun main() = with(System.`in`.bufferedReader()) {
	var N = readLine().toInt()
	var infos = Array(N) { IntArray(3) }
	for (i in 0 until N) {
		infos[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
	}

	var answer = BooleanArray(1000)

	for (info in infos) {
		var num = info[0].toString()
			.let { Triple(it[0].digitToInt(), it[1].digitToInt(), it[2].digitToInt()) }

		// 스트라이크에 따른 숫자 후보 저장
		if (info[1] == 1) {
			for (i in 100..999) {
				var t = i.toString()
					.let { Triple(it[0].digitToInt(), it[1].digitToInt(), it[2].digitToInt()) }

				if (t.first == num.first || t.second == num.second || t.third == num.third
					&& !answer[i]
				) answer[i] = true
			}
		} else if (info[1] == 2) {
			for (i in 100..999) {
				var t = i.toString()
					.let { Triple(it[0].digitToInt(), it[1].digitToInt(), it[2].digitToInt()) }

				// 12 & 23 & 13
				if (t.first == num.first && t.second == num.second
					|| t.third == num.third && t.second == num.second
					|| t.first == num.first && t.third == num.third
					&& !answer[i]
				) answer[i] = true
			}
		} else if (info[1] == 3) {
			println(1)
			return@with
		}

		// 볼에 따른 숫자 후보 저장
		if (info[2] == 1) {
			for (i in 100..999) {
				var t = i.toString()

				if (t.contains(num.first.toChar())
					&& !t.contains(num.second.toChar())
					&& !t.contains(num.third.toChar())
					&& !answer[i]
				) answer[i] = true
				if (t.contains(num.second.toChar())
					&& !t.contains(num.first.toChar())
					&& !t.contains(num.third.toChar())
					&& !answer[i]
				) answer[i] = true
				if (t.contains(num.third.toChar())
					&& !t.contains(num.second.toChar())
					&& !t.contains(num.first.toChar())
					&& !answer[i]
				) answer[i] = true
			}
		} else if (info[2] == 2) {
			for (i in 100..999) {
				var t = i.toString()

				if (!t.contains(num.first.toChar())
					&& t.contains(num.second.toChar())
					&& t.contains(num.third.toChar())
					&& !answer[i]
				) answer[i] = true
				if (!t.contains(num.second.toChar())
					&& t.contains(num.first.toChar())
					&& t.contains(num.third.toChar())
					&& !answer[i]
				) answer[i] = true
				if (!t.contains(num.third.toChar())
					&& t.contains(num.second.toChar())
					&& t.contains(num.first.toChar())
					&& !answer[i]
				) answer[i] = true
			}
		} else if (info[2] == 3){
			for (i in 100..999) {
				var t = i.toString()

				if (t.contains(num.first.toChar())
					&& t.contains(num.second.toChar())
					&& t.contains(num.third.toChar())
					&& !answer[i]
				) answer[i] = true
			}
		}
		else if(info[2] == 0){
			for (i in 100..999) {
				var t = i.toString()

				if (t.contains(num.first.toChar())
					|| t.contains(num.second.toChar())
					|| t.contains(num.third.toChar())
				) answer[i] = false
			}
		}
	}

	println(answer.filterIndexed { index, it -> index in 100 .. 999 && it }.count())
}
