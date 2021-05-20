package com.bambi.jvmCode;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类加载器与instanceOf 关键字演示
 *
 * @author Bambi
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream inputStream = getClass().getResourceAsStream(fileName);
                    if (inputStream == null) {
                        return super.loadClass(name);
                    }
                    byte[] bytes = new byte[inputStream.available()];
                    inputStream.read(bytes);

                    return defineClass(name,bytes,0,bytes.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return super.loadClass(name);
            }
        };

        Object obj = myClassLoader.loadClass("com.bambi.jvmCode.ClassLoaderTest").newInstance();

        System.out.println(obj.getClass());
        System.out.println( obj instanceof  com.bambi.jvmCode.ClassLoaderTest);
    }
}
