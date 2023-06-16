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

import static java.lang.Integer.max;
import static java.lang.Integer.min;

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
                if (arr[i][j] != 0 && i > 0 && j > 0) {
                    arr[i][j] = min(min(arr[i - 1][j - 1], arr[i][j - 1]), arr[i - 1][j]) + 1;
                }
                answer = max(answer, arr[i][j]);
            }
        }

        System.out.println(answer * answer);
    }
}
