class Solution {

    fun solution(gems: Array<String>): IntArray {
        var answer = IntArray(2)
        val gemSet = HashSet<String>()
        var countMap = HashMap<String, Int>() // 범위 내 보석 개수 저장, key: 보석, value: 개수
        var countInRange = 0 // 범위 내 보석 종류 수
        var minLength = gems.size + 1 // 모든 보석 포함하는 최소 구간 길이

        for (gem in gems) {
            gemSet.add(gem)
        }

        var start = 0
        var end = 0

        // [start, end) 형태로 구간 탐색
        // end가 가리키는 곳까지 범위를 늘리고, end를 증가시키는 구조
        // 전체 보석을 포함할 때까지 end를 증가시키고, 
        // 보석 하나가 전부 제거될 때까지 start를 증가시키면서 정답을 비교, 갱신한다 
        while (end < gems.size) {
            val addedGem = gems[end++]
            countMap[addedGem] = countMap.getOrDefault(addedGem, 0) + 1
            if (countMap[addedGem]!! == 1) {
                countInRange++
            }

            // start를 오른쪽으로 한 칸 옮겼는데도 여전히 모든 보석을 포함하는 경우가 존재한다.
            // ex) 범위 내에 start 보석을 2개 이상 갖고 있는 경우
            // 따라서, 보석 하나가 0이 될 때까지 start를 증가시키면서 정답 갱신 조건을 확인한다
            while (countInRange == gemSet.size) {
                if (minLength > end - start) {
                    minLength = end - start
                    answer[0] = start + 1
                    answer[1] = end
                }

                val removedGem = gems[start++]
                countMap[removedGem] = countMap[removedGem]!! - 1
                if (countMap[removedGem]!! == 0) {
                    countInRange--
                }
            }
        }

        return answer
    }
}