package java.파일명정렬;
/*
파일명은 영문자로 시작
헤드 넘버 파일로 구성
헤드: 문자, 한글자 이상, 숫자 아님
넘버: 한글자~다섯글자 사이 연속된 숫자
인풋크기
files 최대 1000개
각 file 최대길이 100
정렬기준
1. 헤드: 사전순 정렬, 대소문자 구분 x
2. 넘버: 숫자순
3. M1.zip , m01.png는 들어온 순서 유지 -> java의 collections는 timsort, 즉 안정정렬이다

Todo
    1. 헤드 넘버 테일 앞에서부터 자르기 -> 정규식? for?
    2. 정렬기준에 맞는 Comparator 만들기
 * */

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Character.isDigit;


class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];

        List<String[]> list = new LinkedList();

        for (int i = 0; i < files.length; i++) {
            list.add(new String[]{"", "", ""});
        }

        for (int idx = 0; idx < files.length; idx++) {
            String file = files[idx];

            // head
            int head = 0;
            while (!isDigit(file.charAt(head))) {
                head++;
            }
            list.get(idx)[0] = file.substring(0, head);

            // number
            int number = head;
            int num5 = 0;
            while (num5 < 5 && number < file.length() && isDigit(file.charAt(number))) {
                number++;
                num5++;
            }
            list.get(idx)[1] = file.substring(head, number);
            if (number >= file.length()) {
                continue;
            }
            list.get(idx)[2] = file.substring(number);
        }

        Comparator<String[]> com = new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                if (o1[0].equalsIgnoreCase(o2[0])) {
                    return Integer.parseInt(o1[1]) - Integer.parseInt(o2[1]);
                }
                return o1[0].toLowerCase().compareTo(o2[0].toLowerCase());
            }
        };

        Collections.sort(list, com);
        for (int i = 0; i < files.length; i++) {
            answer[i] = list.get(i)[0] + list.get(i)[1] + list.get(i)[2];
        }

        return answer;
    }
}
