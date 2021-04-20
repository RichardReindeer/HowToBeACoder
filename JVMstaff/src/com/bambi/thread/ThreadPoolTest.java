package com.bambi.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class ThreadPoolTest {
    //定义一个容量为4的线程池
    private static final ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {
        //定义任务list，创建任务并加入List
        List<Callable<Map<String,Object>>> taskList = new ArrayList<>();
        taskList.add(new Task("url1"));
        taskList.add(new Task("url2"));
        taskList.add(new Task("url3"));
        taskList.add(new Task("url4"));

        //定义返回结果list 通过future获取返回值
        List<Future<Map<String,Object>>> resultList = new ArrayList<>();
        taskList.forEach(task->{
            Future<Map<String , Object>> future = pool.submit(task);
            resultList.add(future);
        });

        resultList.forEach(future -> {
            try {
                System.out.println(future.get(100,TimeUnit.SECONDS).get("url"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        });
    }
}


//线程执行人物类
class Task implements Callable<Map<String,Object>>{
    private String url;

    public Task(String url) {
        this.url = url;
    }

    @Override
    public Map<String, Object> call() throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("url",this.url);
        map.put("result",this.url);
        System.out.println("url = " + this.url);
        return map;
    }
}
