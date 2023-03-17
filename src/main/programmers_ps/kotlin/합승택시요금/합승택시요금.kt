package kotlin.합승택시요금


import java.lang.Integer.min

/*
s에서 출발하여 A와 B를 포함하는 경로의 예상 최저 요금을 구하시오
모든 간선이 연결되어 있지 않을 수도 있다
정점이 갈라질 수도 있다
s->a, s->b를 구하고 중복되는 배열 제외 합치기
s->a의 최소 경로를 구하고, s->a 경로의 노드마다 s->a의 최소 경로 구해서 그 비용이 최소가 될 때...
플로이드 워셜, 모든 정점에서 B, S, A로 향하는 비용을 더하고 최솟값을 리턴하면 될듯

4 -> 1 -> 5
5 -> 6
5 -> 3 -> 2
그래프 초기화를 10만*n으로 해줬더니 ok됨
효율성 테스트가 정확성+효율성 테스트인가보다

100000 200
20000000
정점 200개 이하, 간선 N(N-1)/2 이하 19900
정점 수가 200이니까 플로이드 워셜이 맞을듯
* */

class Solution {
    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        var answer: Int = Int.MAX_VALUE

        var graph = Array(n + 1) { IntArray(n + 1) { 100000 * n } }
        for (i in 0 until n + 1) {
            graph[i][i] = 0
        }

        for (i in fares) {
            graph[i[0]][i[1]] = i[2]
            graph[i[1]][i[0]] = i[2]
        }

        for (k in 1..n) {
            for (i in 1..n) {
                for (j in 1..n) {
                    graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])
                }
            }
        }

        for (i in 1..n) {
            answer = min(answer, graph[i][s] + graph[i][a] + graph[i][b])
        }

        return answer
    }
}