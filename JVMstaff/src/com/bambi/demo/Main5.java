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
 *  1      2021/7/30 17:44    Bambi        Create
 * ****************************************************************************
 * </pre>
 *
 * @author Bambi
 * @since 1.0
 */
public class Main5 {
    public static int arr[][];
    public static int maxX,maxY;
    public static int maxMin;
    public static double res;
    public static int n;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();

        arr = new int[n][2];
        for(int i =0;i<n;i++){
            arr[i][0] = scan.nextInt();
            arr[i][1] = scan.nextInt();
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Math.abs(o1[0]-o1[1])-Math.abs(o2[0]=o2[1]);
            }
        });
        maxX = arr[0][0];
        maxY = arr[0][1];
        for(int i =1 ;i<n;i++){
            int current;
            if(arr[i][0]>arr[i][1]){
                current = arr[i][1]+maxY;
            }else {
                current = arr[i][0]+maxX;
            }
            if(current>maxMin){
                maxMin = current;
            }
            maxX = Math.max(arr[i][0],maxX);
            maxY = Math.max(arr[i][1],maxY);
        }
        res = maxMin/2.0;
        System.out.println(res);
    }
}
