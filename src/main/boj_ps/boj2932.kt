fun main() = with(System.`in`.bufferedReader()) {
	val (N, M) = readLine().split(" ").map { it.toInt() }
	// 목표 위치 저장
	var RClist = Array(M) { shortArrayOf(0, 0) }

	var matrix_r = IntArray(1000)
	var matrix_c = IntArray(1000)

	for (i in 0 until M) {
		val (X, R, C) = readLine().split(" ").map { it.toInt() }
		RClist[i] = shortArrayOf((R - 1).toShort(), (C - 1).toShort())

		// 입력받은 값의 r위치 저장
		matrix_r[i] = (X - 1) / N
		// 입력받은 값의 c위치 저장
		matrix_c[i] = (X - 1) % N
	}

	fun RotateRow(r: Int, p: Int) {
		for (i in 0 until M) {
			if (matrix_r[i] == r) {
				matrix_c[i] = (matrix_c[i] + p) % N
			}
		}
	}

	fun RotateColumn(c: Int, p: Int) {
		for (i in 0 until M) {
			if (matrix_c[i] == c) {
				matrix_r[i] = (matrix_r[i] + p) % N
			}
		}
	}

	for (i in 0 until M) {
		var r_move = RClist[i][0] - matrix_r[i]
		var c_move = RClist[i][1] - matrix_c[i]
		if (r_move < 0) r_move += N
		if (c_move < 0) c_move += N

		RotateRow(matrix_r[i], c_move)
		RotateColumn(matrix_c[i], r_move)

		println(r_move + c_move)
	}

}

