package BJ15000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ15683_감시 {
    private static class CCTV {
        public CCTV(int num, int r, int c) {
            this.num = num;
            this.r = r;
            this.c = c;
        }

        int num, r, c;
    }

    private static int N, M, MIN_COUNT;
    private static int[][] map;
    private static List<CCTV> cctvList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 세로 크기
        M = Integer.parseInt(st.nextToken());  // 가로 크기
        map = new int[N][M];
        cctvList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (1 <= map[i][j] && map[i][j] < 6) {  // CCTV일 경우
                    cctvList.add(new CCTV(map[i][j], i, j));
                } else if (map[i][j] == 0) {
                    MIN_COUNT++;
                }
            }
        }

        // CCTV 감시 확인하기
        findMaxCheck(0, map);
        System.out.println(MIN_COUNT);
    }

    private static void findMaxCheck(int index, int[][] map) {
        if (index == cctvList.size()) {
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0) count++;
                }
            }
            MIN_COUNT = Math.min(count, MIN_COUNT);
            return;
        }

        CCTV cctv = cctvList.get(index);
        int[][] tmpMap = new int[N][M];
        switch (cctv.num) {
            case 1:
                for (int i = 0; i < 4; i++) {
                    copyMap(map, tmpMap);
                    visitCheck(cctv, tmpMap, new int[]{i});
                    findMaxCheck(index + 1, tmpMap);

                }
                break;
            case 2:
                copyMap(map, tmpMap);
                visitCheck(cctv, tmpMap, new int[]{0, 1});
                findMaxCheck(index + 1, tmpMap);

                copyMap(map, tmpMap);
                visitCheck(cctv, tmpMap, new int[]{2, 3});
                findMaxCheck(index + 1, tmpMap);
                break;
            case 3:
                copyMap(map, tmpMap);
                visitCheck(cctv, tmpMap, new int[]{0, 2});
                findMaxCheck(index + 1, tmpMap);

                copyMap(map, tmpMap);
                visitCheck(cctv, tmpMap, new int[]{0, 3});
                findMaxCheck(index + 1, tmpMap);

                copyMap(map, tmpMap);
                visitCheck(cctv, tmpMap, new int[]{1, 2});
                findMaxCheck(index + 1, tmpMap);

                copyMap(map, tmpMap);
                visitCheck(cctv, tmpMap, new int[]{1, 3});
                findMaxCheck(index + 1, tmpMap);
                break;
            case 4:
                for (int i = 0; i < 4; i++) {
                    copyMap(map, tmpMap);
                    visitCheck(cctv, tmpMap, new int[]{(i + 1) % 4, (i + 2) % 4, (i + 3) % 4});
                    findMaxCheck(index + 1, tmpMap);
                }
                break;
            case 5:
                copyMap(map, tmpMap);
                visitCheck(cctv, tmpMap, new int[]{0, 1, 2, 3});
                findMaxCheck(index + 1, tmpMap);
                break;
            default:
                break;
        }

    }

    private static int visitCheck(CCTV cctv, int[][] map, int[] dirs) {
        int check = 0;
        for (int dir : dirs) {
            switch (dir) {
                case 0:
                    for (int i = cctv.c; i < M; i++) {  // 동 방문
                        if (map[cctv.r][i] == 6) break;
                        if (map[cctv.r][i] == 0) {
                            map[cctv.r][i] = -1;
                            check++;
                        }
                    }
                    break;
                case 1:
                    for (int i = cctv.c; 0 <= i; i--) {  // 서 방문
                        if (map[cctv.r][i] == 6) break;
                        if (map[cctv.r][i] == 0) {
                            map[cctv.r][i] = -1;
                            check++;
                        }
                    }
                    break;
                case 2:
                    for (int i = cctv.r; i < N; i++) {  // 남 방문
                        if (map[i][cctv.c] == 6) break;
                        if (map[i][cctv.c] == 0) {
                            map[i][cctv.c] = -1;
                            check++;
                        }
                    }
                    break;
                case 3:
                    for (int i = cctv.r; 0 <= i; i--) {  // 북 방문
                        if (map[i][cctv.c] == 6) break;
                        if (map[i][cctv.c] == 0) {
                            map[i][cctv.c] = -1;
                            check++;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return check;
    }

    private static void copyMap(int[][] map, int[][] tmpMap) {
        for (int i = 0; i < N; i++) {
            if (M >= 0) System.arraycopy(map[i], 0, tmpMap[i], 0, M);
        }
    }
}