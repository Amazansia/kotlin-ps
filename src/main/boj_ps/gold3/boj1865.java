package gold3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
N개의 지점, M개의 도로, W개의 웜홀.
지점은 1~N까지 순서대로, N은 500
도로는 최대 2500개
웜홀은 200개

도로는 무방향, 웜홀은 방향이다.
웜홀을 타면 시간이 뒤로 간다.
한 지점에서 출발해서 시간여행을 하는데, 출발했던 지점으로 돌아왔을 때 출발 시간보다 시간이 뒤로 가게 되는 경우가 있는가?
두 지점을 연결하는 도로는 한 개 이상일 수 있다.
도로가 무방향이고 무조건 가장 짧은 시간이 걸리는 경로만 남기고 계산해도 상관없으므로 입력받을 때 min으로 카운트해줘야 함

웜홀을 우선적으로 탐색해야 함
한 지점에서 출발해서 다시 돌아왔을 때...
사이클이 만들어질 때...

벨만포드 돌려서 음수사이클 생기는지 보면 될듯
출발 정점이 정해져 있지 않으므로 모든 정점에 대해 돌려야 함
인접행렬로 구현
했더니 91%에서 계속 시간초과 남
N 다도는게 문젠가?
실화냐? 모든 정점에서 돌려보니 시초난다
* */
public class boj1865 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static final int INF = 500 * 10001;

	public static void main(String[] args) throws IOException {

		int tc = Integer.parseInt(br.readLine());

		for (int i = 0; i < tc; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			yesOrno(N, M, W);
		}
	}

	private static void yesOrno(int N, int M, int W) throws IOException {
		// 도로 정보 입력
		int[][] path = new int[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(path[i], INF);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());

			path[s][e] = Math.min(path[s][e], t);
			path[e][s] = Math.min(path[e][s], t);
		}
		// 웜홀 정보 입력
		for (int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			path[s][e] = Math.min(path[s][e], -1 * t);
		}

		// 모든 정점에서 벨만-포드 체크
		// 시작 정점을 1부터 N까지 돌려보면서 마지막 N번째에서 갱신이 일어날 경우 음수 사이클이 존재하는 것이다
		// i: 출발점
//		for (int start = 1; start < N; start++) {
			int[] dist = new int[N + 1];
			Arrays.fill(dist, INF);
			dist[1] = 0;

			boolean isUpdated = false;

			for (int i = 1; i < N - 1; i++) {
				isUpdated = false;
				// j to k 탐색하면서 갱신
				for (int j = 1; j <= N; j++) {
//					if (dist[j] == INF) {
//						continue;
//					}
					for (int k = 1; k <= N; k++) {
						// 길이 없음
//						if (path[j][k] == INF) {
//							continue;
//						}

						// 더 작은 경로가 존재할 경우 갱신
						if (dist[k] > dist[j] + path[j][k]) {
							dist[k] = dist[j] + path[j][k];
							isUpdated = true;
						}
					}
				}
				if (!isUpdated) {
					break;
				}
			}
			if (isUpdated) {
				for (int j = 1; j <= N; j++) {
//					if (dist[j] == INF) {
//						continue;
//					}
					for (int k = 1; k <= N; k++) {
						// 길이 없음
//						if (path[j][k] == INF) {
//							continue;
//						}

						// 더 작은 경로가 존재할 경우 갱신
						if (dist[k] > dist[j] + path[j][k]) {
							System.out.println("YES");
							return;
						}
					}
				}
			}
//		}

		System.out.println("NO");
	}
}
