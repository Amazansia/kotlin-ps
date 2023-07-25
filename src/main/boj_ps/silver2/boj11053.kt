package silver2

// list에 수를 계속 추가하면서 만약 list.last() > arr[i]면 list[list.size-1] = arr[i]

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()
    var arr = readLine().split(" ").map { it.toInt() }.toIntArray()

    var list = IntArray(N)
    list[0] = arr[0]

    var idx = 0
    // arr[i]가 들어갈 수 있는 최적의 위치를 찾아봅시다
    // 10 20 10 30 20 50
    // 10 20 30 50
    // 이때 50의 자리는
    for (i in 1 until N) {
        // list에 저장된 현재 원소보다 arr[i]가 작다면 arr[i]로 갱신해야 함
        if (list[idx] > arr[i]) {
            list[idx] = arr[i]
        }
        // list에 저장된 현재 원소보다 arr[i]가 크다면 덧붙여줍시다
        if (list[idx] < arr[i]) {
            list[++idx] = arr[i]
        }
    }

    println(list.count { it != 0 })
}