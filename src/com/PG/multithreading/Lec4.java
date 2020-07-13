package com.PG.multithreading;

public class Lec4 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("inside "+Thread.currentThread().getName());
                throw new RuntimeException("Internal exception");
            }
        });

        thread1.setName("new worker thread");
        //thread1.setPriority(8);
        thread1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("critical error occured" + t.getName() + " error " + e.getMessage());
            }
        });

        System.out.println("b4 starting "+Thread.currentThread().getName());
        thread1.start();
        Thread.sleep(0);
        System.out.println("after starting "+Thread.currentThread().getName());

        //Thread.sleep(100);
    }
}
