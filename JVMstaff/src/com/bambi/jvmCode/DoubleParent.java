package com.bambi.jvmCode;

public class DoubleParent extends ClassLoader{
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
       
        //需要检测请求的类是否被加载过
        Class c = findLoadedClass(name);
        //没有被加载过
        if(c == null){
            try{
                if(getParent()!=null){
                    c = getParent().loadClass(name,false);
                }
            }
        }
        return super.loadClass(name, resolve);
    
    }

    public static void main(String[] args) {
        
    }
}
