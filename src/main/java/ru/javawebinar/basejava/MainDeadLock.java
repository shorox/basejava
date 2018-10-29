package ru.javawebinar.basejava;

public class MainDeadLock {

    public final static Object firstObject = new Object();
    public final static Object secondObject = new Object();
    private static java.lang.Object Object;

    public static void main(String[] args) {

        Thread t1 = createThread(firstObject, secondObject);
        Thread t2 = createThread(secondObject, firstObject);

        t1.start();
        t2.start();
    }

    public static Thread createThread(Object o1, Object o2) {
        return new Thread(() -> {
            System.out.println("Lock first Object in " + Thread.currentThread().getName());
            synchronized (o1) {
                System.out.println("Go to sleep in " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("We sleeping in " + Thread.currentThread().getName());
                System.out.println("Lock second Object in " + Thread.currentThread().getName());
                synchronized (o2) {
                    System.out.println("Lock  " + Thread.currentThread().getName());
                }
            }
        });
    }
}
