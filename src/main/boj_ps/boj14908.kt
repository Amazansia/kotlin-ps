import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()) {
	val N = readLine().toInt()
	var T = IntArray(N)
	var S = IntArray(N)
	repeat(N) {
		var str = readLine().split(" ")
		T[it] = str[0].toInt()
		S[it] = str[0].toInt()
	}
	// 전체 보상금 합을 저장하는 변수
	var sum = S.sum()
	// 현재 날짜를 저장하는 변수
	var current = 0
	// 어떤 작업을 하기로 선택했을 때 피해액 공식: current * (sum - 현재하는작업의보상금)
	// 이 값이 최소가 되는 작업을 최우선적으로 선택해서 해야 함

	// 피해액이 최소가 되는 작업 번호를 리턴하는 함수
	fun minMinus(): Int {
		return 0
	}



}