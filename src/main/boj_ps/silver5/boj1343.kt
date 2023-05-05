package silver5

fun main() = with(System.`in`.bufferedReader()) {
    var board = readLine()
    var length = board.length

    var i = -1

    while (++i < length) {
        if (board[i] == '.') continue
        if (i + 4 <= length && board.substring(i, i + 4) == "XXXX") {
            board = board.replaceRange(i, i + 4, "AAAA")


            i += 3
            continue
        } else if (i + 2 <= length && board.substring(i, i + 2) == "XX") {

            board = board.replaceRange(i, i + 2, "BB")
            i += 1
            continue
        }
    }

    print(if (board.contains("X")) -1 else board)
}