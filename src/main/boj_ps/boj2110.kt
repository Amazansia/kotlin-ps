fun main() = with(System.`in`.bufferedReader()) {
	var (N, C) = readLine().split(" ").map { it.toInt() }
	var arr = IntArray(N)
	repeat(N) {
		arr[it] = readLine().toInt()
	}

	// 일단 sort...
	arr.sort()
	// 인접한 공유기 사이의 최대거리
	// 판별명제: 최소 mid값만큼의 간격으로 공유기를 모두 설치할 수 있는가
	// 해당 명제의 마지막 true가 정답이 된다.
	// mid값의 범위: 1부터 arr.last - arr.first

	var low = 1
	var high = arr.last() - arr.first()

	var mid = 0

	fun isOkayWith(dt: Int): Boolean {
		var remain = C - 1
		var temp = arr.first()
		for (i in arr.indices) {
			if (arr[i] - temp >= dt) {
				temp = arr[i]
				remain--
			}
		}
		if (remain <= 0) return true
		return false
	}

	while (low <= high) {
		mid = (low + high) / 2
		when {
			!isOkayWith(mid) -> high = mid - 1
			else -> low = mid + 1
		}
	}
	print(high)
}