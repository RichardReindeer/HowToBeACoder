package com.bambi.thread.volatileLearn;

public class Arr {
    public static void main(String[] args) {
        Integer[] arr = {1,2,3};
        Integer[] acc = {2,3,4};
        arr = acc;
    }
}
