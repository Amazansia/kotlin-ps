package java.jobda_1;

import java.util.LinkedList;
import java.util.Queue;

class Solution {

    public class Node {
        int x;
        int y;
        int c;

        public Node(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static LinkedList<Integer> answerList = new LinkedList<>();
    static int answer = Integer.MAX_VALUE;

    private void bfs(int[][] M, boolean[][] visited, int now_x, int now_y, int end_x, int end_y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(now_x, now_y, 0));

        visited[now_x][now_y] = true;
        // System.out.println(now_x  + " " +now_y);
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.x == end_x && node.y == end_y) {
                answer = Math.min(node.c, answer);

            }

            for (int i = 0; i < 4; i++) {
                int x = node.x + dx[i];
                int y = node.y + dy[i];

                if (x < 0 || x >= M.length || y < 0 || y >= M[0].length || M[x][y] == 1 || visited[x][y]) {
                    continue;
                }
                visited[x][y] = true;
                q.offer(new Node(x, y, node.c + 1));
            }
        }
    }

    public int solution(int[][] M, int[] S, int[] D) {
        bfs(M, new boolean[M.length][M[0].length], S[1], S[0], D[1], D[0]);

        return answer;
        // if(answer == Integer.MAX_VALUE) answer = -1;
    }
}