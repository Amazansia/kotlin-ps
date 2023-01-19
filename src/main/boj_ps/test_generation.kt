fun main() = with(System.`in`.bufferedReader()) {
    repeat(100) {
        repeat(99) {
            print("0 ")
        }
        print("0")
        println()
    }
}