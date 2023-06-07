package gold1;
/*
1부터 N까지 번호가 매겨진 도시
N개를 모두 거쳐 원래의 도시로 돌아오는 순회 여행경로
한번 간 도시는 다시 방문 불가
비용은 대칭적이지 않을 수 있다. 즉 w[i][j] != w[j][i] 일 수 있음
아무데서나 출발해서 첫 도시로 돌아오면 됨
N은 16
dfs로 백트래킹? 아무튼 모든 경우의 수를 고려해야 하지 않나...
플로이드워셜? 안될듯
자바진짜입출력만한바가지
visited 있어야함
dfs 맞는거같어 아니면 방문체크 못쓰잖아
void dfs: 출발 도시 & visited 배열 & 현재까지 지나온 경로 합 & 이제까지 지나온 노드 카운트
지나온 노드가 N과 같고 현재 노드가 출발 도시라면 최솟값으로 저장...
이러면 최악의 경우 16!만큼 돌지 않나?
경로 없으면 바로 리턴
경로 비용이 작은 순으로 pq에서 순서대로 pop해서 dfs?
그렇다면 첫번째로 답이 갱신되었을 경우 그것이 정답

* */


import static java.lang.Integer.min;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj2098 {

	static int answer = Integer.MAX_VALUE;
	static int N = 0;
	static int[][] cities;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		cities = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				cities[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean[] visited = new boolean[N];
		visited[0] = true;
		dfs(0, visited, 0, 0, 0);

		System.out.println(answer);
	}

	//	void dfs: 출발 도시 & visited 배열 & 현재까지 지나온 경로 합 & 이제까지 지나온 노드 카운트
	// 종료조건: 지나온 노드가 N-1과 같고 현재 노드가 출발 도시라면 최솟값으로 저장...
	private static void dfs(int start, boolean[] visited, int now, int sum, int cityCount) {
		if (sum >= answer) {
			return;
		}

		if (cityCount == N - 1) {
			answer = min(answer, sum + cities[now][start]);
			return;
		}

		PriorityQueue<Integer> pq = new PriorityQueue();
		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				continue;
			}
			if (cities[now][i] != 0) {
				pq.add(i);
			}
		}

		while (!pq.isEmpty()) {
			int next = pq.poll();
			visited[next] = true;
			dfs(start, visited, next, sum + cities[now][next], cityCount + 1);
			visited[next] = false;
		}
	}


}
