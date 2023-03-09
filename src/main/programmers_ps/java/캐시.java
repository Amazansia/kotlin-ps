package java;

import java.util.LinkedList;

/*
LRU(Least Recently Used)는 가장 오랫동안 참조되지 않은 페이지를 교체하는 방식입니다.
캐시 크기와 도시 이름 배열을 받는다.
캐시는 0~30의 정수이다. -> 0일때 조심
도시 개수는 10만개까지 들어올 수 있다.
cache hit -> 실행시간 1
miss -> 실행시간 5
총 실행시간을 구하여라...
doublelinkedlist
* */

class Solution {

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        LinkedList list = new LinkedList();

        for (int i = 0; i < cities.length; i++) {
            String str = cities[i].toLowerCase();

            if (list.contains(str)) {
                list.remove(str);
                answer += 1;
            } else {
                answer += 5;
            }

            list.addFirst(str);

            if (list.size() > cacheSize && list.size() > 0) {
                list.removeLast();
            }
        }

        return answer;
    }
}