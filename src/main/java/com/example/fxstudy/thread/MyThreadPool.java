package com.example.fxstudy.thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IDEA
 * author:Dnetted
 * Date:2019/1/7
 * Time:10:51
 */
public class MyThreadPool {
    public static ThreadPoolExecutor newMyThreadPool(){
        ThreadPoolExecutor tp = new ThreadPoolExecutor(10,10,1,TimeUnit.MINUTES,new LinkedBlockingDeque<Runnable>());
        return tp;
    }
}
