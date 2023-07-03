package gold4

/*
임의의 두 집 사이에 경로가 항상 존재한다............
직선경로가 아니고 경유경로가 반드시 존재한다는 뜻
그러니까 분리된 집이 없어야 한다, 즉 모두 연결되어 있어야 한다
마을 분리하기
길의 유지비의 합을 최소로 하고 싶다
길을 더 없애보자
먼솔?
집들이 서로 연결되도록 분할해야 한다...
남은 길 유지비의 합의 최솟값을 출력한다
유니온파인드 + MST. 크루스칼 알고리즘?
1. 마을을 분리하고 나면 분리된 두 마을 사이의 길은 없앨 수 있다
2. 분리된 마을 안에서도 임의의 두 집 사이 항상 경로가 존재하게 하면서 나머지 필요없는 길을 없앨 수 있다
유지비 합을 최소로 하고 싶다면...
경로 가중치 내림차순 정렬
그리디하게...큰 가중치부터 없애보면서 노드의 시작과 끝을 유니온파인드로
MST의 정의..........?
MST구해서 가장 큰 가중치의 경로를 없애면 되는거아닌가요??????????
* */
fun main() = with(System.`in`.bufferedReader()) {
	var (N, M) = readLine().split(" ").map { it.toInt() }
	var houses = mutableListOf<Triple<Int, Int, Int>>()
	for (i in 0 until M) {
		var (A, B, C) = readLine().split(" ").map { it.toInt() }
		houses.add(Triple(A, B, C))
		houses.add(Triple(B, A, C))
	}

	houses.sortBy { it.third }

	// 집마다 랭크 저장
	var parent = IntArray(N + 1) { it }

	fun find(now: Int): Int {
		if (now == parent[now]) return now
		parent[now] = find(parent[now])
		return parent[now]
	}

	fun union(a: Int, b: Int) {
		var aParent = find(a)
		var bParent = find(b)
		if (aParent < bParent) {
			parent[bParent] = aParent
		} else {
			parent[aParent] = bParent
		}
	}

	var answer = 0
	var maxVal = -1
	for (path in houses) {
		if (find(path.first) != find(path.second)) {
			answer += path.third
			maxVal = Math.max(path.third, maxVal)
			union(path.first, path.second)
		}
	}

	println(answer - maxVal)
//	println(parent.joinToString(" "))
}