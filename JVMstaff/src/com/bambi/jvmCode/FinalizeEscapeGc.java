package com.bambi.jvmCode;

/**
 * 对象自我拯救练习
 *
 *
 * 任何一个对象的finalize()方法都只会被系统自动调用一次
 */
public class FinalizeEscapeGc {
    public static FinalizeEscapeGc SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("yes , i am alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed");
        FinalizeEscapeGc.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGc();
        //对象第一次成功拯救自己
        SAVE_HOOK= null;
        System.gc();
        //因为Finalizer方法优先级很低，暂停0.5s,等待
        Thread.sleep(500);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }
        else {
            System.out.println("no i am dead : (");
        }

        //下面的代码和上面完全相同，但是拯救失败
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if(SAVE_HOOK!=null){
            SAVE_HOOK.isAlive();
        }
        else {
            System.out.println("no i am dead :(");
        }
    }
}
