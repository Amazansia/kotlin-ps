package gold4;
/*
bfs?에 구현?
1인 칸에서 bfs 시작해서 대각선으로 확산
최대 1000 * 1000
1000000...백만
isSquare(length, x, y): boolean

1 2 3 4
2 2 3 4
3 3 3 4
4 4 4 4

* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj1915 {

	static int[][] arr;
	static int N;
	static int M;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		// 정사각형 한 변의 최대 길이 저장
		answer = 0;

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = line.charAt(j) - '0';
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 1) {
					bfs(i, j);
				}
			}
		}
		//System.out.println(arr);
		System.out.println(answer * answer);

	}

	// x, y는 정사각형의 가장 오른쪽 아래라고 가정. 이전의 정사각형은 x-1, y-1이다
	// x, y를 기준으로 가로 왼쪽, 세로 위쪽을 length만큼 확인하면서 1인지 체크하여 정사각형인지 확인하는 함수
	private static boolean isSquare(int x, int y, int length) {
		if (x >= N || y >= M) {
			return false;
		}
		// x,y = 2,2, len=3 0,2/1,2/2,2

		for (int i = length - 1; i >= 0; i--) {
			if (arr[x - i + 1][y] != 1 || arr[x][y - i + 1] != 1) {
				return false;
			}
		}

		return true;
	}

	private static void bfs(int x, int y) {
		int len = arr[x][y];
		int dx = x;
		int dy = y;
		while (isSquare(dx + 1, dy + 1, len + 1)) {
			for (int i = len; i >= 0; i--) {
				arr[dx - i + 1][dy + 1] = len + 1;
				arr[dx + 1][dy - i + 1] = len + 1;
			}
			len++;
			dx++;
			dy++;
			answer = Math.max(answer, len);
		}
	}

}
