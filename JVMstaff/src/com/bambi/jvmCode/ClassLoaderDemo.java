package com.bambi.jvmCode;

/**
 * 类是抽象的，对象是具体的
 */
public class ClassLoaderDemo {
    private int test;
    public static void main(String[] args) {
        //类是抽象的，对象是具体的
        ClassLoaderDemo classLoaderDemo1 = new ClassLoaderDemo();
        ClassLoaderDemo classLoaderDemo2 = new ClassLoaderDemo();
        ClassLoaderDemo classLoaderDemo3 = new ClassLoaderDemo();

        System.out.println(classLoaderDemo1.hashCode());
        System.out.println(classLoaderDemo2.hashCode());
        System.out.println(classLoaderDemo3.hashCode());

        //三者相等
        Class<? extends ClassLoaderDemo> aClass = classLoaderDemo1.getClass();
        Class<? extends ClassLoaderDemo> bClass = classLoaderDemo2.getClass();
        Class<? extends ClassLoaderDemo> cClass = classLoaderDemo3.getClass();


        //获取类加载器
        ClassLoader classLoader = aClass.getClassLoader();
        //
        //classLoader = jdk.internal.loader.ClassLoaders$AppClassLoader@1f89ab83
        System.out.println("classLoader = " + classLoader);
        //jdk.internal.loader.ClassLoaders$PlatformClassLoader@6108b2d7  jre/lib/ext
        //扩展类加载器
        ClassLoader parent = classLoader.getParent();
        System.out.println("parent = " + parent);

        //null有两种情况， 1.不存在，2.java程序获取不到  一般在rt.jar
        ClassLoader parent1 = parent.getParent();
        System.out.println("parent1 = " + parent1);
    }
}
