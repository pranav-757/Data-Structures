package com.PG.multithreading;

public class Inventory {

    public static void main(String[] args) throws InterruptedException{
        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementingThread thread1 = new IncrementingThread(inventoryCounter);
        DecrementingThread thread2 = new DecrementingThread(inventoryCounter);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("number of items: "+inventoryCounter.getItemCount());
    }

    private static class IncrementingThread extends Thread{
        private InventoryCounter inventoryCounter;

        public IncrementingThread(InventoryCounter counter) {
            this.inventoryCounter = counter;
        }

        @Override
        public void run(){
            for(int i=0; i<10000; i++){
                inventoryCounter.increment();
            }
        }
    }

    private static class DecrementingThread extends Thread{
        private InventoryCounter inventoryCounter;

        public DecrementingThread(InventoryCounter counter) {
            this.inventoryCounter = counter;
        }

        @Override
        public void run(){
            for(int i=0; i<10000; i++){
                inventoryCounter.decrement();
            }
        }
    }

    private static class InventoryCounter {
        private int itemCount =0;

        public void increment(){itemCount++;}
        public void decrement(){itemCount--;}

        public int getItemCount() {
            return itemCount;
        }
    }
}