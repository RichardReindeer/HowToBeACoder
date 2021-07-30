package com.bambi.demo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 描述：
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID     DATE          PERSON          REASON
 *  1      2021/7/30 16:30    Bambi        Create
 * ****************************************************************************
 * </pre>
 *
 * @author Bambi
 * @since 1.0
 */
public class Main1 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++)
        {
            int n = sc.nextInt();
            int[][] t = new int[n][2];
            int[] nums = new int[n];
            for (int j = 0; j < n; j++)
            {
                t[j][0] = sc.nextInt();
            }
            for (int j = 0; j < n; j++)
            {
                t[j][1] = sc.nextInt();
            }
            Arrays.sort(t, new Comparator<int[]>()
            {
                @Override
                public int compare(int[] o1, int[] o2)
                {
                    if(o1[0] > o2[0])
                        return 1;
                    else if(o1[0] < o2[0])
                        return -1;
                    else
                    {
                        if(o1[1] > o2[1])
                            return -1;
                        else if(o1[1] < o2[1])
                            return 1;
                        else
                            return -1;
                    }
                }
            });

            for (int j = 0; j < n; j++)
            {
                nums[j] = t[j][1];
            }
            int result = longestSubArray(nums);
            System.out.println(result);

        }
    }
    public static int longestSubArray(int[] nums)
    {
        int[] tails = new int[nums.length];
        int res = 0;
        for (int num:nums)
        {
            int l = 0;
            int r = res;
            while(l < r)
            {
                int m = l + (r - l)/2;
                if(tails[m] < num)
                    l = m + 1;
                else
                    r = m;
            }
            tails[l] = num;
            if(r == res)
                res++;
        }
        return  res;
    }
}
