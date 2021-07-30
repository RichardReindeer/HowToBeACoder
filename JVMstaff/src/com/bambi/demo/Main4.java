package com.bambi.demo;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * 描述：
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID     DATE          PERSON          REASON
 *  1      2021/7/30 17:31    Bambi        Create
 * ****************************************************************************
 * </pre>
 *
 * @author Bambi
 * @since 1.0
 */
public class Main4 {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][][] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] tmp = line.split(" ");
        int m = Integer.parseInt(tmp[0]);
        int n = Integer.parseInt(tmp[1]);
        char[][] map = new char[m][n];
        int sx = 0, sy = 0, ex = 0, ey = 0;
        for (int i = 0; i < m; i++) {
            line = scanner.nextLine();
            for (int j = 0; j < n; j++) {
                char ch = line.charAt(j);
                map[i][j] = ch;
                if (ch == 'S') {
                    sx = i;
                    sy = j;
                }
                if (ch == 'E') {
                    ex = i;
                    ey = j;
                }
            }
        }
        bfs(map, sx, sy, ex, ey);
        for (int i = 0; i < 6; i++) {
            if (dp[ex][ey][i] != 0) {
                System.out.println(dp[ex][ey][i]);
                return;
            }
        }
        System.out.println(-1);
    }

    public static void

    bfs(char[][] map, int sx, int sy, int ex, int ey) {
        Deque<int[]> queue = new ArrayDeque<>();
        int m = map.length;
        int n = map[0].length;
        dp = new int[m][n][6];

        queue.offerFirst(new int[]{
                sx, sy, 0
        });
        while (!queue.isEmpty()){
            int[] status = queue.pollLast();
            int x = status[0], y = status[1], t = status[2];
            int nx = 0, ny = 0, nt = 0;
            for (int i = 0; i < 5; i++) {
                if (i == 4) {
                    nx = m - 1 - x;
                    ny = n - 1 - y;
                    nt = t + 1;
                } else {
                    nx = x + dx[i];
                    ny = y + dy[i];
                    nt = t;
                }
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && nt < 6 && map[nx][ny] != '#' && dp[nx][ny][nt] == 0) {
                    dp[nx][ny][nt] = dp[x][y][t] + 1;
                    if (nx == ex && ny == ey) {
                        return;
                    }
                    queue.offerFirst(new int[]{nx, ny, nt});
                }
            }
        }
    }
}
