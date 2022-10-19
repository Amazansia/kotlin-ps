import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()) {
	// "지금까지 말한 수 중에서" 중간값
	// 말한 수 개수가 짝수라면 두 수중 작은 수
	// 범위는 -10000 ~ 10000
	// N개 입력, N은 100000까지
	// 중복 가능하고 원소를 추가할 때마다 자동 정렬되는...

	val N = readLine().toInt()
	// 큰 값 우선
	// 10 9 8 7
	var firstqueue = PriorityQueue<Int>(reverseOrder())
	// 작은 값 우선
	// 1 2 4 5
	var secondqueue = PriorityQueue<Int>()
	val strbuilder = StringBuilder()

	// 두 큐 사이즈가 같도록 번갈아가면서 원소를 넣어준다
	// firstqueue의 첫 번째 값과 secondqueue의 첫 번째 값을 삽입 시마다 비교
	// if(firstqueue.peek() > secondqueue.peek()) -> 서로 값을 스왑해준다
	// 이러면 firstqueue의 최댓값 <= secondqueue의 최솟값
	// else 적절한 값을 출력
	for (i in 1..N) {
		val temp = readLine().toInt()
		if (i % 2 == 1) {
			firstqueue.add(temp)
		} else {
			secondqueue.add(temp)
		}

		if (!secondqueue.isEmpty() && firstqueue.peek() > secondqueue.peek()) {
			var swap_first = firstqueue.poll()
			var swap_second = secondqueue.poll()
			secondqueue.add(swap_first)
			firstqueue.add(swap_second)
		}
		strbuilder.append(firstqueue.peek().toString() + "\n")
	}

	println(strbuilder.toString())
}