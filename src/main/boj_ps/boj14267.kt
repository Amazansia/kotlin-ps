fun main() = with(System.`in`.bufferedReader()) {
    var (N, M) = readLine().split(" ").map { it.toInt() }
    // 0 ~ N-1
    var immediateSupervisor = readLine().split(" ").map { it.toInt() }.toIntArray()
    var complimentArray = IntArray(N)

    // 1. 사장(0번)을 제외한 모든 직원은 상사가 있다
    // 2. 상사-부하 관계에서 상사가 칭찬을 받으면 부하에게 칭찬이 더해진다.
    // 3. gotCompliment가 오름차순 정렬일 때, 부하의 칭찬 수치를 계산할 때 상사의 칭찬 수치는 업데이트되지 않는다.
    // 4. 즉, 미리 계산된 수치를 쓰면 된다.

    // 칭찬받은 직원 & 칭찬 수치 저장
    var gotCompliment = IntArray(N)
    repeat(M) {
        var (i, w) = readLine().split(" ").map { it.toInt() }
        // 번호 correcting
        gotCompliment[i - 1] += w
    }

    for (i in gotCompliment.indices) {
        // 칭찬받았을 경우 본인의 직속 상사가 받은 칭찬 + 칭찬 수치 더해서 complimentArray에 저장
        complimentArray[i] += gotCompliment[i]
        // 사장일 경우 continue
        if (immediateSupervisor[i] == -1) {
            continue
        }
        complimentArray[i] += complimentArray[immediateSupervisor[i] - 1]
    }

    var sb = StringBuilder()
    complimentArray.forEach {
        sb.append("$it ")
    }
    print(sb)
}