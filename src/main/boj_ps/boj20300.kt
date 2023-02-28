fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().trim().toInt()
    var arr = readLine().trim().split(" ").map { it.toLong() }.toLongArray()
    /*
    하루에 2개씩 운동기구를 모두 사용해야 하고, 순서는 상관없다
    근손실 정도의 한계값이 최소가 되도록...
    일단 정렬하고
    홀-> 마지막 원소를 제외하고 1부터 size-1까지 순회했을 때 양끝을 더한 값과 마지막 원소를 비교
    짝-> 1부터 size-1까지 순회했을 때 양끝을 더한 값의 최댓값
    ? 이게끝?
    * */

    var answer = arr.last()
    var size = arr.size

    arr.sort()

    if (arr.size % 2 == 1) {
        size--
    }
    // size =
    for (i in 0 until size / 2) {
        answer = kotlin.math.max(answer, arr[i] + arr[size - i - 1])
    }

    print(answer)
}