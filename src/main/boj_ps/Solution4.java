import static java.lang.Integer.min;

public class Solution4 {
    public int solution(int n, int m, int k) {
        /*
        n개의 차선으로 이루어진, 전체 너비가 m인 도로
        각 차선의 너비는 k 이하인 자연수
        전체 너비의 최솟값은 n*1, 최댓값은 n*k
        n * (1~k) == m이 되는 모든 방법...
        가능한 모든 방법을 나타낼 것
        즉 1~k인 자연수 n개를 더해 m이 될 수 있는 모든 경우의 수를 구하는 것
        1, 2와 2, 1은 서로 다른 경우다 == 즉, 순서를 구분한다
        n,m,k는 1~100인 자연수이다.
        dp...BottomUp
        * * */
        int end = min(n, m);
        int MOD = 1000007;
        int[][] dpArr = new int[n + 1][m + 1];
        for (int i = 1; i <= end; i++) {
            dpArr[1][i] = 1;
        }
        dpArr[1][0] = 0;
        // 자연수이므로 0은 초기화하지 않는다
        for (int i = 1; i < n + 1; i++) {
            dpArr[i][1] = 1;
        }

        for (int i = 1; i <= n; i++) {
            // 모든 칸을 채우되
            for (int j = 2; j <= m; j++) {
                // j-k ~ j-1까지 돌아야...
                for (int z = k; z >= 1; z--) {
                    // 범위 내에 들어올 경우
                    if (j - z > 0 && j - z < m) {
                        dpArr[i][j] += dpArr[i - 1][j - z];
                        dpArr[i][j] %= MOD;

                    }
                }
//                dpArr[i][j] = dpArr[i - 1][j] + dpArr[i][j - 1];
            }
        }

        if (n > m || n * k < m)
            return 0;
        else
            return dpArr[n][m];
    }

//    public static void main(String[] args) {
//        Solution s = new Solution();
//        s.solution(10, 6, 5);
//    }
}
