package com.bambi.stringIntern;

public class InternTest {
    public static void main(String[] args) {
        String n = new String("abd");
        n.intern();
        String n2 = n.intern();
        System.out.println("n2 = " + n2);
        /*String n1  = new String("abd");
        n1.intern();*/
        String n1 = "abd";
        System.out.println(n1==n2);
    }
}
