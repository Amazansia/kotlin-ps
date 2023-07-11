package silver2

fun main() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(" ").map { it.toInt() }

	// 목표 위치 저장(최종적으로 어디로 보내야 하는지)
    var RClist = Array(M) { shortArrayOf(0, 0) }

    var matrix_r = IntArray(1000)
    var matrix_c = IntArray(1000)

    for (i in 0 until M) {
        val (X, R, C) = readLine().split(" ").map { it.toInt() }
        RClist[i] = shortArrayOf((R - 1).toShort(), (C - 1).toShort())

        // 입력받은 값의 초기 r위치 저장
        matrix_r[i] = (X - 1) / N
        // 입력받은 값의 초기 c위치 저장
        matrix_c[i] = (X - 1) % N
    }

	// r값을 기준으로 행렬을 회전시킬 때 영향을 받는 다른 수가 있는지 확인해야 한다
	// 있을 경우 그 수도 회전시켜주고 최종 위치를 저장해 주어야 함
    fun RotateRow(r: Int, p: Int) {
        for (i in 0 until M) {
            if (matrix_r[i] == r) {
                matrix_c[i] = (matrix_c[i] + p) % N
            }
        }
    }

	// RotateRow()와 동일
    fun RotateColumn(c: Int, p: Int) {
        for (i in 0 until M) {
            if (matrix_c[i] == c) {
                matrix_r[i] = (matrix_r[i] + p) % N
            }
        }
    }

    for (i in 0 until M) {
		// 목표 위치까지 얼마나 움직여야 하는지 저장
        var r_move = RClist[i][0] - matrix_r[i]
        var c_move = RClist[i][1] - matrix_c[i]
		// OutOfRange일 때 N을 더해서 범위 안으로 넣어준다(회전하기 때문)
        if (r_move < 0) r_move += N
        if (c_move < 0) c_move += N

        RotateRow(matrix_r[i], c_move)
        RotateColumn(matrix_c[i], r_move)

        println(r_move + c_move)
    }
}