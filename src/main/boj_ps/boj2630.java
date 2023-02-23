import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2630 {

	static int white = 0;
	static int blue = 0;

	public static void main(String[] args) throws IOException {
		// 재귀로...

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] paper = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		count(paper, N, 0, 0);
		System.out.print(white+"\n"+blue);
	}

	// 색종이, 배열 사이즈, start_X, start_Y
	static void count(int[][] paper, int num, int x, int y) {
		// 하얀색 0, 파란색 1
		// 색이 중간에 섞여 있을 경우 재귀호출로 한번 더 자른다
		int color = paper[x][y];
		for (int i = x; i < num + x; i++) {
			for (int j = y; j < num + y; j++) {
				if (paper[i][j] != color) {
					count(paper, num / 2, x, y);
					count(paper, num / 2, x, y + num / 2);
					count(paper, num / 2, x + num / 2, y);
					count(paper, num / 2, x + num / 2, y + num / 2);
					return;
				}
			}
		}
		if (color == 0) {
			white++;
		} else {
			blue++;
		}
	}
}
