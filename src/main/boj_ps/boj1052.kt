/*
k개를 넘지 않는 차 있는 물병
같은 양의 물이 들어 있는 물병 2개를 고르고 하나의 물병에 물을 몰아 붓는다
N개로 K개를 넘지 않는 비어있지 않은 물병을 만드는 것이 불가능할 수 있다
N은 10000000, k는 1000
상점에서 사는 물병은 물이 1리터 들어 있다...
2048?
3 -> 1 1
4 -> 1 0
4 -> 2 0
4 -> 3 0
4 -> 4 0
5 -> 1 3
7 -> 1 1
5 -> 2 0
100으로 나올 수 있는 수의 최솟값
3

6 -> 1 2
6 -> 2 0 1 1 1 1 1 1 2 2 2 4 2
6 -> 3 0
6 -> 4 0
6 -> 5 0
6 -> 6 0
8 -> 1 0
2진수 변환?
16 1 -> 0
16 2
16 3
16 4
16 5
16 6 2 2 2 2 4 4
16 7 1 1 2 4 4 4
16 8
비어있지 않은 물병: 나머지
1000 1001
100000 1000
frontOneCount -> 1111 0000 0000 -> 4
f K
4 1
4 3 -> 1 0000 0000 0000
이 값이 K보다 크다면 len +1 더해주고... 빼준다
작다면
4 5 -> 걍 return 0
k보다 크다면 ...
* */
fun main() = with(System.`in`.bufferedReader()) {
    var (N, K) = readLine().trim().split(" ").map { it.toInt() }
//    if (N <= K) {
//        print(0)
//        return@with
//    }
//    var answer = 0
//
//    while (true) {
//        var count = 0
//        var temp = N
//        while (temp > 0) {
//            if (temp % 2 == 1) {
//                count++
//            }
//            temp /= 2
//        }
//        if (count <= K) break
//        N++
//        answer++
//    }
    var answer = 0
    while (N.countOneBits() > K) {
        var t = 1
        while (N and t != t) {
            t = t shl 1
        }
        N += t
        answer += t
    }
    print(answer)

}