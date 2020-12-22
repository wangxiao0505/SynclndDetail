package com.wangxiao.juc.c_020;

import java.util.concurrent.TimeUnit;

/**
 * @author wangxiao
 * @desc reentrantlock用于替代synchronized
 * 本例中由于m1锁定this，只有m1执行完毕的时候，m2才能执行
 * 这里是复习synchronized最原始的语义
 * @date 2020-12-21 20:36:37
 */
public class T01_ReentrantLock1 {
    synchronized void m1(){
        for(int i=0;i<10;i++){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            if(i==2){ //synchronized可重入
                m2();
            }
        }
    }

    synchronized void m2(){
        System.out.println("m2---");
    }

    public static void main(String[] args) {
        T01_ReentrantLock1 t = new T01_ReentrantLock1();
        new Thread(t::m1,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //new Thread(t::m2,"t2").start();
    }
}