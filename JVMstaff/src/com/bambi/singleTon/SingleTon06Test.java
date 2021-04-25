package com.bambi.singleTon;

public class SingleTon06Test {
    public static void main(String[] args) {
        SingleTon06 single1 = SingleTon06.getInstance();
        SingleTon06 single2 = SingleTon06.getInstance();
        System.out.println("single2 = " + single2);
        System.out.println("single1 = " + single1);
        System.out.println(single1==single2);
    }
}
