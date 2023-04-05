package java.jobda_1;//package java.jobda_1;

/*
n번의 도네: ~10만
Ai가 Bi원만큼 후원
g1에 속한 시청자의 도네: C%의 세금
g2에 속한 시청자의 도네: 세금 X
도네 종합 D*|G2|^2의 세금을 내야 한다.
총 도네 수는 10만인데, 여기서 같은 시청자가 여러 번 도네를 할 수도 있다.
전처리 0. 같은 시청자일 경우 도네액을 종합하고 linkedList로 만들어 준다
1. set의 조합을 구해야 한다.
10만의 조합을 구한다는 것은 어렵다.
조합을 구하지 말고...
그리디한 선택으로 도네액이 큰 순부터 G2에 밀어넣어 보자.
D * G2.size는 한번에 계산 가능.
C * dona는 누적합 가능
g2의 사이즈가 0일 때(즉, 모든 도네가 g1)부터 set.size(즉, 모든 도네가 g2)까지 순회하면서
tax_g2 + tax_g1을 계산해서 최솟값을 리턴.
D는 100만
* */

import java.util.*;

class Solution {

//    class Pair implements Comparable<Pair> {
//        String first;
//        long second;
//
//        public Pair(String first, long second) {
//            this.first = first;
//            this.second = second;
//        }
//
//        @Override
//        public int compareTo(@NotNull Pair o) {
//            return Long.compare(this.second, o.second);
//        }
//    }

    public long solution(int n, int c, int d, String[] a, int[] b) {
        long answer = Arrays.stream(b).sum();

        SortedMap<String, Long> donationList = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            long temp = donationList.getOrDefault(a[i], 0L) + b[i];
            donationList.put(a[i], temp);
        }
        List<Map.Entry<String, Long>> list = new LinkedList<>(donationList.entrySet());

        // 오름차순 정렬
        list.sort(Map.Entry.comparingByValue());

//        그리디한 선택으로 도네액이 작은 순으로 G1에 밀어넣어 보자.
//        D * G2.size는 한번에 계산 가능.
//        C * dona는 누적합 가능
//        g2의 사이즈가 list.size일 때(즉, 모든 도네가 g2)부터 0(즉, 모든 도네가 g1)까지 순회하면서
//        tax_g2 + tax_g1을 계산해서 minTax와 비교해 최솟값을 리턴.

        long minTax = Long.MAX_VALUE;
        long g2Size = list.size();
        long g1Tax = 0L;

        for (int i = 0; i < list.size(); i++) {
            long g2Tax = d * g2Size * g2Size;
            g1Tax += c * list.get(i).getValue();

            minTax = Math.min(g1Tax + g2Tax, minTax);
            g2Size -= 1;
        }


        return answer - minTax;
    }
}