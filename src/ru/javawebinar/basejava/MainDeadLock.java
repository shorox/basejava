package ru.javawebinar.basejava;

public class MainDeadLock {

    public final static Object firstObject = new Object();
    public final static Object secondObject = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            System.out.println("Lock first Object in t1");
            synchronized (firstObject) {
                System.out.println("Go to sleep in t1");
                Thread.yield();
                System.out.println("We sleeping in t1");
                System.out.println("Lock second Object in t1");
                synchronized (secondObject) {
                    System.out.println("Lock t1!");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            System.out.println("Lock second Object in t2");
            synchronized (secondObject) {
                System.out.println("Go to sleep in t2");
                Thread.yield();
                System.out.println("We sleeping in t2");
                System.out.println("Lock first Object in t2");
                synchronized (firstObject) {
                    System.out.println("Lock t2!");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
