package com.bambi.thread.cyclicBarrrier;


import java.util.concurrent.*;

public class CyclicBarrierDemo02 implements Runnable{

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(3);


    @Override
    public void run() {
        try {
            System.out.println("到达了栅栏");
            TimeUnit.SECONDS.sleep(15);
            cyclicBarrier.await(10L,TimeUnit.SECONDS);
            System.out.println("等待结束，继续执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        for(int i =0;i<3;i++){
            service.execute(new CyclicBarrierDemo02());
        }
    }
}
