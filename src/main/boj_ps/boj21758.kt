/*
   벌은 벌통까지 날아가면서 지나가는 모든 칸에서 꿀을 딴다
   벌이 시작한 장소에서는 꿀을 딸 수 없다
   벌 두 마리를 두는 장소를 정하고, 한 장소를 골라서 벌통을 둔다.
   벌이 시작한 장소에서는 어떤 벌도 꿀을 딸 수 없다(시간순서 상관 없음)
   꿀을 안 따는 장소가 있으면 안됨 -> 양쪽 끝과 중간 어딘가를 정해야 함
   3가지 배치밖에 없음
   1. 왼쪽끝 벌, 중앙 벌, 오른쪽끝 벌통
   2. 왼쪽끝 벌통, 중앙 벌, 오른쪽끝 벌
   3. 왼쪽끝 벌, 중앙 벌통, 오른쪽끝 벌

   중앙에 벌통이 있는 경우는 중복이 안 됨... 벌1 벌2 합쳐서 총 N-2개의 장소에서 꿀을 딸 수 있음
   오른쪽 끝에 벌통이 있는 경우는 중복이 가능함. 벌1 벌2 합쳐서 최대 N-1 + N-2개의 장소에서 꿀을 딸 수 있다.
   결국 문제의 조건을 만족하는 경우는 위에서 1번의 경우. 중앙 벌 위치만 잘 조정하면 될 듯
   N이 10만, 꿀의 양은 최대 1만...1 000 000 000 -> 총합 10억. int 범위 이내!
   배열을 차례대로 순회하면서...
   양 끝값 중 큰 값이 꿀통의 값이 되어야 한다 -> 큰 값은 end에, 작은 값은 start에 저장
   왼쪽 start의 경우...
   첫번째 벌: sum - arr[0] - arr[i]
   중앙 벌: sum - arr[0] - arr[i] - for k in 1 until i sum -= arr[k]
   오른쪽 start의 경우
   첫: sum - arr[size-1] - arr[i]
   중: sum - arr[size-1] - arr[i] - for k in size-1 downTo i sum -= arr[k]
   i를 어떻게 구하나요...
   9 4 1 4 9 5칸 중 하나
   i = 1
   _ 4 1 4 9 / _ 4 1 4 9 -> sum - arr[1] - middleSum:1
   i = 3
   9 4 _ 4 9 / _ _ _ 4 9 -> sum - arr[3] - middleSum:3 중 큰 값 저장
   answer = (sum - arr[]) * 2
   ---
   */

fun main() = with(System.`in`.bufferedReader()) {

    var answer: Long = 0
    var N = readLine().trim().toInt()
    var arr = readLine().trim().split(" ").map { it.toInt() }.toIntArray()
    val sum: Long = arr.sum().toLong()
    var middleSum = 0

    // 1: 벌 벌 꿀
    var sum1 = sum - arr[0]
    for (i in 1 until N - 1) {
        middleSum += arr[i]
        answer = kotlin.math.max(sum1 * 2 - arr[i] - middleSum, answer)
    }

    // 2: 꿀 벌 벌
    middleSum = 0
    var sum2 = sum - arr[N - 1]
    for (i in N - 2 downTo 1) {
        middleSum += arr[i]
        answer = kotlin.math.max(sum2 * 2 - arr[i] - middleSum, answer)
    }

    // 3: 벌 꿀 벌
    var sum3 = sum - arr[0] - arr[N - 1]
    for (i in 1 until N - 1) {
        answer = kotlin.math.max(answer, sum3 + arr[i])
    }

    print(answer)
}