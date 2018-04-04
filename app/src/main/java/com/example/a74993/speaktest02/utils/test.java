package com.example.a74993.speaktest02.utils;

/**
 * Created by Administrator on 2018/4/3.
 */

public class test {
    public static class SyncThread extends Thread{
        SyncThread(String name){
            super(name);
        }

        @Override
        public void run() {
            //执行耗时操作

        }
    }


    public static void main(String args[])
    {
        SyncThread syncThread1 = new SyncThread("线程一");
        SyncThread syncThread2 = new SyncThread("线程二");
        SyncThread syncThread3 = new SyncThread("线程三");

        syncThread1.start();
        syncThread2.start();
        syncThread3.start();
    }
}
