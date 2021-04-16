package com.bambi.jvmDeep.copy;

/**
 * 测试SystemArrayCopy复制
 */
public class SystemArrayCopy {
    public static void main(String[] args) {
        Integer[] arr = {2,4,6,7,9};
        Integer[] arr1 = new Integer[20];
        System.arraycopy(arr,0,arr1,0,arr.length);
        System.out.println("arr1 = " + arr1);
        System.out.println("arr = " + arr);
    }
}
