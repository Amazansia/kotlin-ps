package silver5

import java.lang.StringBuilder

/*
키 몸무게 둘다 크면 덩치가 크다고 말한다
자신보다 더 큰 덩치의 사람의 수로 정해진다...
* */
fun main() = with(System.`in`.bufferedReader()) {
	var N = readLine().toInt()
	var list = mutableListOf<Pair<Int, Int>>()
	repeat(N) {
		var temp = readLine().split(" ").map { it.toInt() }
		list.add(temp[0] to temp[1])
	}

	var sb = StringBuilder()

	for(i in 0 until N){
		var rank = 1
		for(j in 0 until N){
			if(list[i].first < list[j].first && list[i].second < list[j].second) rank++
		}
		sb.append("$rank ")
	}

	println(sb)
}