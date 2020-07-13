package com.PG.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lec5 {
    private static final int MAX_PASSWRD = 5000;
//    public static void main(String[] args) throws InterruptedException {
//        Thread thread1 = new NewThread(); //concrete thread implementation
//
//        System.out.println("b4 starting "+Thread.currentThread().getName());
//        thread1.start();
//        Thread.sleep(0);
//        System.out.println("after starting "+Thread.currentThread().getName());
//    }
//
//    private static class NewThread extends Thread {
//        @Override
//        public void run() {
//            System.out.println("inside "+ this.getName());
//        }
//    }

    public static void main(String[] args) {
        Random random = new Random();

        Vault v = new Vault(random.nextInt(MAX_PASSWRD));

        List<Thread> threads = new ArrayList<>();

        threads.add(new AscendingHacker(v));
        threads.add(new DescendingHacker(v));
        threads.add(new PoliceThread());

        for(Thread t: threads) {
            t.start();
        }
    }

    private static class Vault {
        private int password;

        public Vault(int password) {
            this.password = password;
        }

        public boolean guessPassword(int guess) {
            try{
                Thread.sleep(5);
            } catch (InterruptedException e) {

            }
            return  this.password == guess;
        }
    }

    private static abstract class HackerThread extends Thread {
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start() {
            System.out.println("starting new thread "+this.getName());
            super.start();
        }
    }

    public static class AscendingHacker extends HackerThread {
        public AscendingHacker(Vault vault) {
            super(vault);
        }

        @Override
        public void run(){
            for(int i=0; i<=MAX_PASSWRD; i++) {
                if(i%100 == 0)
                    System.out.println(this.getName() + " is trying to guess the password " + i);
                if(vault.guessPassword(i)){
                    System.out.println("hacker " + this.getName() +" cracked the vault");
                    System.exit(0);
                }
            }
        }
    }

    public static class DescendingHacker extends HackerThread {
        public DescendingHacker(Vault vault) {
            super(vault);
        }

        @Override
        public void run(){
            for(int i=MAX_PASSWRD; i>0; i--) {
                if(i%100 == 0)
                    System.out.println(this.getName() + " is trying to guess the password " + i);
                if(vault.guessPassword(i)){
                    System.out.println("hacker " + this.getName() +" cracked the vault");
                    System.exit(0);
                }
            }
        }
    }

    public static class PoliceThread extends Thread {
        @Override
        public void run() {
            for(int i=10; i>0; i--){
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                System.out.println("seconds left" + i);
            }

            System.out.println("game over");
            System.exit(0);
        }
    }
}
