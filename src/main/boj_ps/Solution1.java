public class Solution1 {
    public int solution(String[] bakery_schedule, String current_time, int k) {
        int arrLen = bakery_schedule.length;

        String[] now_time_arr = current_time.split(":");
        // 분으로 환산한 current_time
        int now_time = Integer.parseInt(now_time_arr[0]) * 60 + Integer.parseInt(now_time_arr[1]);
        // 시간 더해주기
        int answer = 0;
        int sum_B = 0;
        for (int i = 0; i < arrLen; i++) {
            String temp[] = bakery_schedule[i].split(" ");
            String time_arr[] = temp[0].split(":");
            int time = Integer.parseInt(time_arr[0]) * 60 + Integer.parseInt(time_arr[1]);

            // 빵이 나오는 시각이 이미 지났으면 continue
            if (time < now_time) {
                continue;
            }

            answer = time - now_time;
            sum_B += Integer.parseInt(temp[1]);

            // 빵이 k만큼 팔렸다면 종료
            if (sum_B >= k) {
                return answer;
            }
        }

        return -1;
    }

}
