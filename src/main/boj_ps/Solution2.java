import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution2 {
    public int[] solution(int[] p, int[] b) {
        /*
        한 조직원 당 한 명의 보스
        본인의 보스가 없으면 최종보스
        이 조직원이 최종보스라면 해당 조직의 조직원 수(최종보스 보함)를 리턴
        최종보스가 아니라면(p[b] != -1) 이라면 -1 리턴
        b가 10만...이러면 dp 써야 함
        순서도 없음...bfs? + 10만이니 dp...
        트리 만들어야 함
        * */

        // tree
        ArrayList<Integer>[] tree = new ArrayList[p.length];
        for (int i = 0; i < p.length; i++) {
            tree[i] = new ArrayList<>();
        }

        // 보스 -> 조직원
        for (int i = 0; i < p.length; i++) {
            if (p[i] != -1) {
                tree[p[i]].add(i);
            }
        }

        int[] dpArray = new int[p.length];
        Arrays.fill(dpArray, 0);

        int[] answer = new int[b.length];

        for (int i = 0; i < b.length; i++) {
            // 최종보스가 아니라면
            if (p[b[i]] != -1) {
                answer[i] = 0;
            }
            // 보스가 맞다면 아래 조직원들의 수 bfs 계산
            else {
                int boss = b[i];

                Queue<Integer> q = new LinkedList<>();
                q.add(boss);
                int sum = 0;
                while (!q.isEmpty()) {
                    int now = q.poll();
                    sum++;

                    for (int next : tree[now]) {
                        if (dpArray[now] == 0) {
                            q.add(next);
                        } else {
                            sum += dpArray[now];
                        }
                    }
                }
                dpArray[boss] = sum;
                answer[i] = sum;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution2 s2 = new Solution2();
        int[] p = new int[100000];
        for (int i = 0; i < 100000; i++) {
            p[i] = i + 1;
        }
        p[99999] = -1;
        int[] b = new int[100000];
        Arrays.fill(b, 99999);
        s2.solution(p, b);
    }
}

