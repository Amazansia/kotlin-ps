import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class test {

    static List<Title> titles;
    static int indexSize;

    static class Title {
        String name;
        int power;

        public Title(String name, int power) {
            this.name = name;
            this.power = power;
        }

        @Override
        public String toString() {
            return "[ name : " + name + ", power : " + power + " ]";
        }
    }


    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int titleCount = Integer.parseInt(st.nextToken());
        int powerCount = Integer.parseInt(st.nextToken());

        titles = new ArrayList<>();
        int prePower = 0;
        for (int i = 0; i < titleCount; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int power = Integer.parseInt(st.nextToken());

            if (prePower == power) continue;

            prePower = power;
            titles.add(new Title(name, power));
        }
        indexSize = titles.size();

//        System.out.println(titles.toString());

        for (int i = 0; i < powerCount; i++) {
            int inputPower = Integer.parseInt(br.readLine());
            sb.append(getName(inputPower)).append("\n");
        }

        System.out.print(sb.toString());
    }

    static String getName(int power) {
        int lo = 0;
        int hi = indexSize - 1;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
//            System.out.println("현재 탐색하는 인덱스 : " + mid);

            if (mid >= indexSize) {
                mid = indexSize - 1;
            }

            if (titles.get(mid).power >= power) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        //IndexOutOfRange 때문에 써줬는데, 이런 경우가 있나?
        if (lo >= indexSize) {
            lo = indexSize - 1;
        }

        return titles.get(lo).name;
    }
}
