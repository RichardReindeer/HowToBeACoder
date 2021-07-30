package com.bambi.demo;

import java.util.Scanner;

/**
 * 描述：
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID     DATE          PERSON          REASON
 *  1      2021/7/30 17:21    Bambi        Create
 * ****************************************************************************
 * </pre>
 *
 * @author Bambi
 * @since 1.0
 */
public class Main2 {
    static int mod = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int groups = sc.nextInt();
        while (groups-- > 0) {
            long a = sc.nextInt();
            long b = sc.nextInt();
            int n = sc.nextInt();
            a %= mod;
            b %= mod;
            System.out.println(new Main2().solve(a, b, n));
        }
    }

    long solve(long a, long b, int n) {
        long[] dp = new long[n + 1];
        dp[0] = 2;
        dp[1] = a;
        dp[2] = a * a % mod - 2 * b % mod;
        for (int i = 3;
             i <= n;
             i++) {
            dp[i] = (a * (dp[i - 1]) % mod - b * (dp[i - 2]) % mod + mod) % mod;
        }
        return dp[n];
    }
}