package BJ01000;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ01931_회의실배정 {
    static class Meeting implements Comparable<Meeting> {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            if(this.end != o.end) return this.end - o.end;
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Meeting[] meetingTime = new Meeting[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            meetingTime[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(meetingTime);

        int selTime = meetingTime[0].end, cnt = 1;
        for (int i = 1; i < N; i++) {
            if(selTime > meetingTime[i].start) continue;
            cnt++;
            selTime = meetingTime[i].end;
        }

        sb.append(cnt);
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
